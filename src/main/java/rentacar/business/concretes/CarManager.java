package rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.var;
import rentacar.business.abstracts.CarService;
import rentacar.business.dtos.car.CarDto;
import rentacar.business.dtos.car.GetCarByDailyPriceDto;
import rentacar.business.dtos.car.ListCarDto;
import rentacar.business.requests.car.CreateCarRequest;
import rentacar.business.requests.car.DeleteCarRequest;
import rentacar.business.requests.car.UpdateCarRequest;
import rentacar.core.exception.BusinessException;
import rentacar.core.utilities.mapping.ModelMapperService;
import rentacar.core.utilities.results.DataResult;
import rentacar.core.utilities.results.Result;
import rentacar.core.utilities.results.SuccessDataResult;
import rentacar.core.utilities.results.SuccessResult;
import rentacar.dataAccess.abstracts.CarDao;
import rentacar.entities.concretes.Car;

@Service
public class CarManager implements CarService{

	private CarDao carDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		// super();
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public Result create(CreateCarRequest createCarRequest) throws BusinessException {
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		
		return new SuccessDataResult<CreateCarRequest>(createCarRequest, "Car named"+ car.getCarName() + "is added");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
		
		checkCarId(deleteCarRequest.getCarId());
		
		this.carDao.deleteById(deleteCarRequest.getCarId());
		
		return new SuccessResult("Brand is deleted ");
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {
		
		checkCarId(updateCarRequest.getCarId());
		checkCarName(updateCarRequest.getCarName());
		
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
			this.carDao.save(car);

		return new SuccessDataResult<UpdateCarRequest>(updateCarRequest,
					"Car is updated : " + car.getCarName());

	}

	@Override
	public DataResult<List<ListCarDto>> getAll() {
		var result = this.carDao.findAll();
		List<ListCarDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<CarDto> getById(int carId) throws BusinessException {
		checkCarId(carId);
		
		Car car = this.carDao.getById(carId);
		CarDto carDto = this.modelMapperService.forDto().map(car, CarDto.class);
		
		return new SuccessDataResult<CarDto>(carDto, "Founded by" + carId);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		List<Car> result=this.carDao.findAll(pageable).getContent();
		List<ListCarDto> response = result.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarDto>>(response);
			
	}

	@Override
	public DataResult<List<ListCarDto>> getAllSorted() {
		
			Sort sort = Sort.by(Sort.Direction.ASC, "dailyPrice");
			
			List<Car> result = this.carDao.findAll(sort);
			List<ListCarDto> response = result.stream().map(car->this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
			
			return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<List<GetCarByDailyPriceDto>> getCarByDailyPriceLessThanEqual(double dailyPrice) {
		
		List<Car> result = this.carDao.getByDailyPriceLessThanEqual(dailyPrice);
		List<GetCarByDailyPriceDto> response = result.stream().map(car->this.modelMapperService.forDto().map(car, GetCarByDailyPriceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetCarByDailyPriceDto>>(response);
	}

	private void checkCarId(int carId) throws BusinessException {
		if (!carDao.getByCarId(carId)) {
			throw new BusinessException("Car with id" + carId + "is not exist");
		}
	}
	
	private void checkCarName(String carName) throws BusinessException {
		if (!carDao.getByCarName(carName)) {
			throw new BusinessException("Car with name" + carName + "is not exist");
		}
	}


	@Override
	public boolean isCarExistsById(int carId) {
		// TODO Auto-generated method stub
		return false;
	}

}