import { Component } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EjerciciosService } from '../../ejercicios.service';
import { Ejercicio } from '../../ejercicio';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-aniadir-ejercicio-rutina',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './aniadir-ejercicio-rutina.component.html',
  styleUrl: './aniadir-ejercicio-rutina.component.css'
})
export class AniadirEjercicioRutina {

  ejercicios!: Ejercicio[];

  nuevoEjercicio: {
    series: number,
    repeticiones: number,
    duracionMinutos: number,
    ejercicio: Ejercicio
  } = {
    series: 0,
    repeticiones: 0,
    duracionMinutos: 0,
    ejercicio: {
      nombre: '',
      descripcion: '',
      observaciones: '',
      musculos_trabajados: '',
      tipo: '',
      material: '',
      dificultad: '',
      multimedia: [],
      id: 0
    }
  };
 
  constructor(private modalService: NgbModal, public activeModal: NgbActiveModal, private ejerciciosService: EjerciciosService) {}
  ngOnInit(): void {
    this.ejercicios = this.ejerciciosService.ejercicios;
  }
  aniadirEjercicio(){
    this.activeModal.close(this.nuevoEjercicio);
  }
}
