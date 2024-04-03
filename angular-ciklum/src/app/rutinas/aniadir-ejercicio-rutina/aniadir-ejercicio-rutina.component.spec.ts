import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AniadirEjercicioRutinaComponent } from './aniadir-ejercicio-rutina.component';

describe('AniadirEjercicioRutinaComponent', () => {
  let component: AniadirEjercicioRutinaComponent;
  let fixture: ComponentFixture<AniadirEjercicioRutinaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AniadirEjercicioRutinaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AniadirEjercicioRutinaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
