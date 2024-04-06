import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { EjerciciosService } from '../../ejercicios.service';
import { Ejercicio } from '../../ejercicio';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-aniadir-ejercicio-rutina',
  templateUrl: './aniadir-ejercicio-rutina.component.html',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  styleUrls: ['./aniadir-ejercicio-rutina.component.css']
})
export class AniadirEjercicioRutina implements OnInit {

  ejercicios: Ejercicio[] = [];
  
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

  constructor(public activeModal: NgbActiveModal, private ejerciciosService: EjerciciosService) {}

  ngOnInit(): void {
    this.ejercicios = this.ejerciciosService.ejercicios;
  }

  aniadirEjercicio(): void {
    this.activeModal.close(this.nuevoEjercicio);
  }

  camposCompletos(): boolean {
    if (this.nuevoEjercicio.series && this.nuevoEjercicio.repeticiones && this.nuevoEjercicio.duracionMinutos !== 0 && this.nuevoEjercicio.ejercicio.nombre!== ''){
      return true;
    }else {
      return false;
    }
  }
}
