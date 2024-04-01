import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DetallesRutina } from './detalles-rutina/detalles-rutina';
import { EditarRutina } from './editar-rutina/editar-rutina';
import { CrearRutina } from './crear-rutina/crear-rutina';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-rutinas',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './rutinas.component.html',
  styleUrl: './rutinas.component.css'
})
export class RutinasComponent implements OnInit {
  rutinas: any[] = [
    {
      nombre: 'Rutina 1',
      descripcion: 'Descripción de la rutina 1',
      observaciones: 'Observaciones de la rutina 1',
      ejercicios: ['Ejercicio 1', 'Ejercicio 2', 'Ejercicio 3']
    },
    {
      nombre: 'Rutina 2',
      descripcion: 'Descripción de la rutina 2',
      observaciones: 'Observaciones de la rutina 2',
      ejercicios: ['Ejercicio 4', 'Ejercicio 5', 'Ejercicio 6']
    }
  ]; 

  nuevaRutina: any = {
    nombre: '',
    descripcion: '',
    observaciones: '',
    ejercicios: []
  }; 

  rutinaSeleccionada: any = null; 

  editarRutinaIndex: number = -1;

  constructor(private modalService: NgbModal) {}

  ngOnInit(): void {
    this.rutinas = this.rutinas;
  }

  agregarRutina() {
    this.rutinas.push({ ...this.nuevaRutina });
    this.nuevaRutina = {
      nombre: '',
      descripcion: '',
      observaciones: '',
      ejercicios: []
    };
  }

  mostrarDetalles(rutina: any): void {
    const modalRef = this.modalService.open(DetallesRutina);
    modalRef.componentInstance.rutina = rutina;
  }

  editarRutina(rutina: any): void {
    const modalRef = this.modalService.open(EditarRutina);
    modalRef.componentInstance.rutina = rutina;
    modalRef.componentInstance.accion = "Editar";
    modalRef.result.then((r: any) => {
      let indice = this.rutinas.findIndex(c => c.nombre == r.nombre);
      this.rutinas[indice] = r;
    }, (reason) => {});
  }


  actualizarRutina() {
    this.rutinas[this.editarRutinaIndex] = { ...this.nuevaRutina };
    this.editarRutinaIndex = -1;
    this.nuevaRutina = {
      nombre: '',
      descripcion: '',
      observaciones: '',
      ejercicios: []
    };
  }


  eliminarRutina(nombre: string) {
    let indice = this.rutinas.findIndex(c => c.nombre == nombre);
    this.rutinas.splice(indice, 1);
  }

  crearRutina(){
    const modalRef = this.modalService.open(CrearRutina);
    modalRef.componentInstance.accion = "Crear";
    modalRef.result.then((r: any) => {
      this.rutinas.push(r);
    }, (reason) => {});
    
  }
}
