import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Ejercicio } from '../../ejercicio';
import { EjercicioComponent } from '../../ejercicios/ejercicios.component'; // OJO: TIENE QUE SER UN SERVICE

@Component({
  selector: 'app-crear-rutina',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './crear-rutina.component.html',
  styleUrls: ['./crear-rutina.component.css']
})

export class CrearRutina implements OnInit {
  nuevaRutina: any = {nombre: '', detalles: '', observaciones: ''};
  accion?: "Crear";
  ejercicios!: Ejercicio[];

  constructor( public activeModal: NgbActiveModal, private ejercicioService: EjercicioComponent) {}

  ngOnInit(): void {
    this.ejercicios = this.ejercicioService.ejercicios;
  }

  editarRutina(){
    this.activeModal.close(this.nuevaRutina);
  }

}