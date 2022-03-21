package rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rentacar.business.abstracts.CarMaintenanceService;
import rentacar.business.abstracts.CarService;
import rentacar.business.abstracts.RentalService;
import rentacar.business.dtos.carMaintenance.CarMaintenanceDto;
import rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import rentacar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import rentacar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.mapping.ModelMapperService;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;
import rentacar.core.utilities.results.SuccessDataResult;
import rentacar.core.utilities.results.SuccessResult;
import rentacar.dataAccess.abstracts.CarDao;
import rentacar.dataAccess.abstracts.CarMaintenanceDao;
import rentacar.dataAccess.abstracts.RentalDao;
import rentacar.entities.concretes.Car;
import rentacar.entities.concretes.CarMaintenance;
import rentacar.entities.enums.CarStates;

@Service
public class CarMaintenanceManager implements CarMaintenanceService{

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private RentalDao rentalDao;
	private CarDao carDao;
	private CarMaintenanceService carMaintenanceService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			RentalDao rentalDao, RentalService rentalService, CarDao carDao, CarService carService, CarMaintenanceService carMaintenanceService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carDao = carDao;
		this.carMaintenanceService = carMaintenanceService;

	}

	@Override
	public Result create(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {
		checkIfCarExistsById(createCarMaintenanceRequest.getCarId());
		checkIfRentalExistsById(createCarMaintenanceRequest.getCarId());
		
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
        Car car = this.carDao.getById(createCarMaintenanceRequest.getCarId());
        car.setCarStates(CarStates.IN_MAINTENANCE);
        this.carMaintenanceDao.save(carMaintenance);
        this.carDao.save(car);

        return new SuccessResult("Car maintenance with id: " + carMaintenance.getCarMaintenanceId() + "is created");
    }
		
	
	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {
		
		checkCarMaintenanceId(updateCarMaintenanceRequest.getCarMaintenanceId());
		checkIfRentalExistsById(updateCarMaintenanceRequest.getCarId());
		checkIfCarMaintenanceExists(updateCarMaintenanceRequest.getCarId());
		checkIfCarExistsById(updateCarMaintenanceRequest.getCarId());
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest()
				.map(updateCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult("Car Maintenance with id " + carMaintenance.getCarMaintenanceId() + "is updated");
		}

	@Override
	public Result delete(int carId) throws BusinessException {
			checkIfCarMaintenanceExists(carId);
			carMaintenanceDao.deleteById(carId);
			return new SuccessResult("Car Maintenance is deleted");
		}

    @Override
    public DataResult<List<ListCarMaintenanceDto>> getAll() {

        List<CarMaintenance> carMaintenances = this.carMaintenanceDao.findAll();
        List<ListCarMaintenanceDto> listCarMaintenanceDto = carMaintenances.stream().map(carMaintenance -> 
        	this.modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarMaintenanceDto>>(listCarMaintenanceDto,listCarMaintenanceDto.size()+" times car maintenances was found");
    }
	@Override
	public DataResult<CarMaintenanceDto> getById(int carMaintenanceId) throws BusinessException {

        checkCarMaintenanceId(carMaintenanceId);

        CarMaintenance carMaintenance = this.carMaintenanceDao.getById(carMaintenanceId);
        CarMaintenanceDto carMaintenanceDto = this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class);

        return new SuccessDataResult<CarMaintenanceDto>(carMaintenanceDto,"Car maintenance is found.");
    }
	

	@Override
	public DataResult<List<ListCarMaintenanceDto>> getCarMaintenanceByCarId(int carId) {
		
		List<CarMaintenance> result = this.carMaintenanceDao.getByCarId(carId);
			
			List<ListCarMaintenanceDto> response = result.stream().map(
					carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class))
					.collect(Collectors.toList());
			
			return new SuccessDataResult<List<ListCarMaintenanceDto>>(response, "Success");
	}
	
	private void checkCarMaintenanceId(int carMaintenanceId) throws BusinessException {
		
		if(!carMaintenanceDao.existsById(carMaintenanceId)) {
			throw new BusinessException("Car Maintenance with id" + carMaintenanceId + "is not exist");
		}

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


}
