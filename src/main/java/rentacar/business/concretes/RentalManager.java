package rentacar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import lombok.var;
import rentacar.business.abstracts.AdditionalServiceService;
import rentacar.business.abstracts.CarMaintenanceService;
import rentacar.business.abstracts.CarService;
import rentacar.business.abstracts.RentalService;
import rentacar.business.dtos.additionalService.ListAdditionalServiceDto;
import rentacar.business.dtos.car.CarDto;
import rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import rentacar.business.dtos.rental.ListRentalDto;
import rentacar.business.dtos.rental.RentalDto;
import rentacar.business.requests.rental.CreateRentalRequest;
import rentacar.business.requests.rental.UpdateRentalRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.mapping.ModelMapperService;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;
import rentacar.core.utilities.results.SuccessDataResult;
import rentacar.core.utilities.results.SuccessResult;
import rentacar.dataAccess.abstracts.AdditionalServiceDao;
import rentacar.dataAccess.abstracts.CarDao;
import rentacar.dataAccess.abstracts.RentalDao;
import rentacar.entities.concretes.AdditionalService;
import rentacar.entities.concretes.Car;
import rentacar.entities.concretes.Rental;
import rentacar.entities.enums.CarStates;

@Service
public class RentalManager implements RentalService{

	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CarMaintenanceService carMaintenanceService;
	private CarService carService;
	private CarDao carDao;
	private AdditionalServiceService additionalServiceService;
	private AdditionalService additionalService;

	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarMaintenanceService carMaintenanceService, 
			CarService carService, CarDao carDao, AdditionalServiceService additionalServiceService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceService = carMaintenanceService;
		this.carService = carService;
		this.carDao = carDao;
		this.additionalServiceService = additionalServiceService;
	}

	@Override
	public DataResult<List<ListRentalDto>> getAll(){
		
		var result = this.rentalDao.findAll();
	
		List<ListRentalDto> response = result.stream()
				.map(rental -> this.modelMapperService.forDto().map(rental, ListRentalDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public DataResult<RentalDto> getById(int rentalId) throws BusinessException {
		checkIfRentalExistsById(rentalId);
		
		Rental rental = rentalDao.getById(rentalId);
		
		RentalDto response = modelMapperService.forDto().map(rental, RentalDto.class);
		return new SuccessDataResult<RentalDto>(response);
	}

	@Override
	public Result create(CreateRentalRequest createRentalRequest) throws BusinessException {
		
		checkIfCarExistsById(createRentalRequest.getCarId());
		checkIfRentalExistsById(createRentalRequest.getRentalId());
		checkIfCarMaintenanceExists(createRentalRequest.getCarId());
		
		
		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		
		rental.setTotalPrice(calculateTotalDailyPrice(rental.getRentalId()));
		
		rentalDao.save(rental);
		
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException {

		checkIfCarExistsById(updateRentalRequest.getCarId());
		checkIfRentalExistsById(updateRentalRequest.getRentalId());

		Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		
		calculateTotalDailyPrice(rental.getRentalId());
		updateCarConditions(rental, updateRentalRequest);
		this.rentalDao.save(rental);
		
		return new SuccessDataResult<UpdateRentalRequest>(updateRentalRequest,
				"Data updated : " + Integer.toString(rental.getRentalId())+ " and total price is : "
						+ Double.toString(rental.getTotalPrice()));
	}

	@Override
	public Result delete(int rentalId) {
		checkIfRentalExistsById(rentalId);
		rentalDao.deleteById(rentalId);
		return new SuccessResult("Rental is deleted");
	}

	@Override
	public DataResult<List<ListRentalDto>> getAllByCar(int carId) throws BusinessException {
		if (!carService.isCarExistsById(carId)) {
			throw new BusinessException("The car with id : " + carId + " was not found!");
		}

		List<Rental> result = rentalDao.getAllByCarCarId(carId);
		List<ListRentalDto> response = result.stream()
				.map(rental -> modelMapperService.forDto().map(rental, ListRentalDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}
	
	private void checkIfCarExistsById(int carId) throws BusinessException {
		
		if(!this.carDao.existsById(carId)) {
			throw new BusinessException("Car with id" + carId + "is not exist");
		}

	}

	private void checkIfRentalExistsById(int rentalId) throws BusinessException {

        if (!this.rentalDao.existsById(rentalId)) {
        	
			throw new BusinessException("Rent with id" + rentalId + "is not exist");
        }
    }

	
	
	public boolean checkIfCarMaintenanceExists(int carId) {
		
		DataResult<List<ListCarMaintenanceDto>> result = this.carMaintenanceService.getCarMaintenanceByCarId(carId);
		
		if (!result.isSuccess()) {
			return true;
		}
		
		for (ListCarMaintenanceDto listCarMaintenanceDto : result.getData()) {
			if (listCarMaintenanceDto.getReturnDate() == null) {
				throw new BusinessException("The car cannot be rented because of its maintenance.");
			}
		}
		return true;
	}

	
	private void updateCarConditions(Rental rental, UpdateRentalRequest updateRentalRequest) {
		
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setReturnKilometer(updateRentalRequest.getReturnKilometer());	
		
	}
	
	private double calculateTotalDailyPrice(Rental rental) {
		
		CarDto car = this.carService.getById(rental.getCars().getCarId()).getData();
		List<ListAdditionalServiceDto> additionalServiceDtos = additionalServiceService.getServicePrice(dailyPrice);
		
		long dateBetween = ChronoUnit.DAYS.between(rental.getRentDate(), rental.getReturnDate());
		if(dateBetween==0) {
			dateBetween=1;
		}
		
		double rentPrice=car.getDailyPrice();
		double totalPrice=rentPrice*dateBetween;
		
		if(additionalServiceDtos.size() > 0) {
			for (ListAdditionalServiceDto additionalServiceDto : additionalServiceDtos) {
				totalPrice += additionalService.getAdditionalServicePrice();
			}
		}
		
		if(rental.getRentCity().getCityPlate() != rental.getReturnCity().getCityPlate()) {
			totalPrice += 750;
		}
		
		return totalPrice;
	}


}