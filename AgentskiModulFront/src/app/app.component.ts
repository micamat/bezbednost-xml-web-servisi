import { Component, ViewContainerRef, Inject } from '@angular/core';
import { LoggedInService } from './main/services/loggedIn.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  //title = 'AgentskiModulFront';


  //constructor(@Inject(LoggedInService) service, 
             // @Inject(ViewContainerRef) viewContainerRef) {
    // service.setRootViewContainerRef(viewContainerRef)
    // service.addDynamicComponent()
  //}
}
