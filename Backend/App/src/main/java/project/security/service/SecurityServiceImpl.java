package project.security.service;

import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{

	@Override
	public Boolean hasAccessTo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@Autowired
	private AirlineRepository airlineRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private FlightConfigurationRepository configurationRepository;
	
	@Autowired
	private FlightSeatCategoryRepository categoryRepository;
	
	@Autowired
	private FlightSegmentRepository segmentRepository;
	
	@Autowired
	private LuggageInfoRepository luggageRepository;
	
	@Autowired
	private FlightSeatsRepository seatRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private CatalogueRepository catalogueRepository;
	
	@Autowired
	private ExtraOptionRepository optionsRepository;
	
	@Autowired
	private FloorRepository floorsRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomReservationRepository roomReservationRepository;
	
	@Autowired
	private RoomTypeRepository roomTypeRepository;
	
	@Autowired
	private RentACarRepository rentACarRepository;
	
	@Autowired
	private BranchOfficeRepository branchOfficeRepository;
	
	@Autowired
	private VehicleRepository vehiclesRepository;
	
	@Autowired
	private VehicleReservationRepository vehicleReservationRepository;
	
	@Autowired
	private CompanyAdminRepository companyAdminRepository;
	
	private CompanyAdmin getCurrentAdmin() {
		CompanyAdmin admin = companyAdminRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if(admin != null) {
			return admin;
		}
		return null;
	}

	public Boolean isCurrentAdmin(Long id) {
		Optional<CompanyAdmin> admin = companyAdminRepository.findById(id);
		CompanyAdmin currentAdmin = companyAdminRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if(admin.isPresent()) {
			return admin.get().getUsername().equals(currentAdmin.getUsername());
		}
		return false;
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Boolean hasAccessToAirline(Long companyId) {
		Optional<Airline> airline = airlineRepository.findById(companyId);
		if(airline.isPresent()) {
				return airline.get().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;
	}
	
	public Boolean hasAccessToFlight(Long flightId) {
		Optional<Flight> flight = flightRepository.findById(flightId);
		if(flight.isPresent()) {
				return flight.get().getAirline().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;
	}
	
	public Boolean hasAccessToConfiguration(Long configurationId) {
		Optional<FlightConfiguration> configuration = configurationRepository.findById(configurationId);
		if(configuration.isPresent()) {
				return configuration.get().getAirline().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;
	}
	
	public Boolean hasAccessToCategory(Long categoryId) {
		Optional<FlightSeatCategory> category = categoryRepository.findById(categoryId);
		if(category.isPresent()) {
				return category.get().getAirline().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;		
	}
	
	public Boolean hasAccessToSegment(Long segmentId) {
		Optional<FlightSegment> segment = segmentRepository.findById(segmentId);
		if(segment.isPresent()) {
				return segment.get().getConfiguration().getAirline().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;			
	}
	
	public Boolean hasAccessToLuggage(Long luggageInfoId) {
		Optional<LuggageInfo> luggage = luggageRepository.findById(luggageInfoId);
		if(luggage.isPresent()) {
				return luggage.get().getAirline().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;	
	}
	
	public Boolean hasAccessToSeat(Long seatId) {
		Optional<FlightSeat> seat = seatRepository.findById(seatId);
		if(seat.isPresent()) {
				return seat.get().getFlight().getAirline().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;	
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Boolean hasAccessToHotel(Long companyId) {
		Optional<Hotel> hotel = hotelRepository.findById(companyId);
		if(hotel.isPresent()) {
				return hotel.get().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;
	}
	
	public Boolean hasAccessToCatalog(Long catalogId) {
		Optional<Catalogue> catalogue = catalogueRepository.findById(catalogId);
		if(catalogue.isPresent()) {
			for(Hotel hotel : catalogue.get().getHotels())
				if(hotel.getAdmins().contains(this.getCurrentAdmin()))
					return true;
			return false;
		}
		return false;		
	}

	public Boolean hasAccessToOption(Long optionId) {
		Optional<ExtraOption> option = optionsRepository.findById(optionId);
		if(option.isPresent()) {
			return option.get().getHotel().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;			
	}
	
	public Boolean hasAccessToFloor(Long floorId) {
		Optional<Floor> floor = floorsRepository.findById(floorId);
		if(floor.isPresent()) {
			return floor.get().getHotel().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;		
	}
	
	public Boolean hasAccessToRoom(Long roomId) {
		Optional<Room> room = roomRepository.findById(roomId);
		if(room.isPresent()) {
			return room.get().getFloor().getHotel().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;			
	}
	
	public Boolean hasAccessToRoomReservation(Long roomReservationId) {
		Optional<RoomReservation> roomReservation = roomReservationRepository.findById(roomReservationId);
		if(roomReservation.isPresent()) {
			return roomReservation.get().getRoom().getFloor().getHotel().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;	
	}
	
	public Boolean hasAccessToRoomType(Long roomTypeId) {
		Optional<RoomType> roomType = roomTypeRepository.findById(roomTypeId);
		if(roomType.isPresent()) {
			for(Hotel hotel : roomType.get().getCatalogue().getHotels())
				if(hotel.getAdmins().contains(this.getCurrentAdmin()))
					return true;
			return false;
		}
		return false;			
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Boolean hasAccessToRentACar(Long companyId) {
		Optional<RentACar> rentACar = rentACarRepository.findById(companyId);
		if(rentACar.isPresent()) {
				return rentACar.get().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;
	}
	
	public Boolean hasAccessToBranchOffice(Long officeId) {
		Optional<BranchOffice> office = branchOfficeRepository.findById(officeId);
		if(office.isPresent()) {
				return office.get().getRentACar().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;
	}
	
	public Boolean hasAccessToVehicle(Long vehicleId) {
		Optional<Vehicle> vehicle = vehiclesRepository.findById(vehicleId);
		if(vehicle.isPresent()) {
				return vehicle.get().getBranchOffice().getRentACar().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;
	}
	
	public Boolean hasAccessToVehicleReservation(Long reservationId) {
		Optional<VehicleReservation> reservation = vehicleReservationRepository.findById(reservationId);
		if(reservation.isPresent()) {
				return reservation.get().getVehicle().getBranchOffice().getRentACar().getAdmins().contains(this.getCurrentAdmin());
		}
		return false;
	}*/
	
}
