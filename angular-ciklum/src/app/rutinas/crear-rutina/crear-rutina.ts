import { Component, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Ejercicio } from '../../ejercicio';
import { EjerciciosService } from '../../ejercicios.service';
import { Rutina } from '../../rutina';
import { AniadirEjercicioRutina } from '../aniadir-ejercicio-rutina/aniadir-ejercicio-rutina.component';

@Component({
  selector: 'app-crear-rutina',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './crear-rutina.component.html',
  styleUrls: ['./crear-rutina.component.css']
})

export class CrearRutina implements OnInit{

  nuevaRutina: Rutina = {
    nombre: '',
    descripcion: '',
    observaciones: '',
    ejercicios: [{
        series: 0,
        repeticiones: 0,
        duracionMinutos: 0,
        ejercicio:
        {
          nombre : '',
          descripcion : '',
          observaciones : '',
          musculos_trabajados : '',
          tipo: '',
          material : '',
          dificultad : '',
          multimedia : [],
          id : 0
        }
    }],
    id: 0
    };
  accion?: "Crear";
  
  ejercicios!: Ejercicio[];

  constructor(private modalService: NgbModal, public activeModal: NgbActiveModal, private ejerciciosService: EjerciciosService) {}

  ngOnInit(): void {
    this.ejercicios = this.ejerciciosService.ejercicios;
  }

  aniadirEjercicio(){
    const modalRef = this.modalService.open(AniadirEjercicioRutina);
    modalRef.componentInstance.accion = "Aniadir";
    modalRef.result.then((ej: {series: number; repeticiones: number; duracionMinutos: number; ejercicio: Ejercicio;}) => {
      console.log(ej);
      this.nuevaRutina.ejercicios.push(ej);
    }, (reason) => {
      console.log('No se añadió ningún ejercicio');
    });
    
  }

  crearRutina(){
    this.activeModal.close(this.nuevaRutina);
  }

}