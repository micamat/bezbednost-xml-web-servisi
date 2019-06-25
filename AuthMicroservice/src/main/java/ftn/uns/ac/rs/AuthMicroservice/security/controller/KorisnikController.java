package ftn.uns.ac.rs.AuthMicroservice.security.controller;

public class KorisnikController {
	/*@Autowired
	KorisnikService korisnikService;
	
	private AuthenticationManager authManager;
	private final JwtConfig jwtConfig = new JwtConfig();
	
	JwtUsernameAndPasswordAuthenticationFilter filter = new JwtUsernameAndPasswordAuthenticationFilter(authManager, jwtConfig);
	
	@PostMapping("/register")
	public ResponseEntity<Korisnik> save(@RequestBody KorisnikDTO korisnik){
		return new ResponseEntity<Korisnik>(korisnikService.save(korisnik), HttpStatus.OK);
	}
	
	@PostMapping("/prijava")
	public ResponseEntity<String> signin(@RequestBody AgentLoginDTO agent){
		System.out.println("BLA BLA BLA=================");
		System.out.println("username: " + agent.getusername() + ", password: " + agent.getpassword());
		return new ResponseEntity<String>(filter.signin(agent.getusername(), agent.getpassword()), HttpStatus.OK);
	}
	
	@PutMapping("/logout")
	public ResponseEntity logout(){
		korisnikService.logout();
		return new ResponseEntity(HttpStatus.OK);
	}*/
}
