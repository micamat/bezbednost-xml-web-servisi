import {
    ComponentFactoryResolver,
    Injectable,
    Inject,
    ReflectiveInjector
  } from '@angular/core'
  import { LoggedInComponent } from '../loggedIn/loggedIn.component'
  @Injectable()
  export class LoggedInService {
      factoryResolver;
      rootViewContainer;
      
    constructor(@Inject(ComponentFactoryResolver) factoryResolver) {
      this.factoryResolver = factoryResolver
    }
    setRootViewContainerRef(viewContainerRef) {
      this.rootViewContainer = viewContainerRef
    }
    addDynamicComponent() {
      const factory = this.factoryResolver
                          .resolveComponentFactory(LoggedInComponent)
      const component = factory
        .create(this.rootViewContainer.parentInjector)
      this.rootViewContainer.insert(component.hostView)
    }
  }