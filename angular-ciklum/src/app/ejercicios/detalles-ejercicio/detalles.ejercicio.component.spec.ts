import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesEjercicioComponent } from './detalles.ejercicio.component';

describe('DetallesEjercicioComponent', () => {
  let component: DetallesEjercicioComponent;
  let fixture: ComponentFixture<DetallesEjercicioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetallesEjercicioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetallesEjercicioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
