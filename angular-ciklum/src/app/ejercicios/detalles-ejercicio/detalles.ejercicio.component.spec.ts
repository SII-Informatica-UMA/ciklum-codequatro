import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesEjercicio } from './detalles.ejercicio.component';

describe('DetallesEjercicio', () => {
  let component: DetallesEjercicio;
  let fixture: ComponentFixture<DetallesEjercicio>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetallesEjercicio]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetallesEjercicio);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
