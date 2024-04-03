import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DetallesRutina } from './detalles-rutina/detalles-rutina';
import { EditarRutina } from './editar-rutina/editar-rutina';
import { CrearRutina } from './crear-rutina/crear-rutina';
import { FormsModule } from '@angular/forms';
import { Rutina } from '../rutina';
import { EjerciciosService } from '../ejercicios.service';
import { Ejercicio } from '../ejercicio';

@Component({
  selector: 'app-rutinas',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './rutinas.component.html',
  styleUrl: './rutinas.component.css'
})
export class RutinasComponent implements OnInit {
  rutinas2: any[] = [
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


  rutinas: Rutina[] = [
    {
        nombre: 'Rutina de calentamiento',
        descripcion: 'Rutina para calentar el cuerpo antes del entrenamiento principal.',
        observaciones: 'Realizar con cuidado y atención a la técnica.',
        ejercicios: [
            {
                series: 2,
                repeticiones: 10,
                duracionMinutos: 5,
                ejercicio: { 
                  nombre: 'Press militar',
                  descripcion: 'Ejercicio de press militar',
                  observaciones: 'Mantener la espalda recta',
                  tipo: 'Brazos',
                  musculos_trabajados: 'Hombros, tríceps',
                  material: 'Barra, discos',
                  dificultad: 'Intermedia',
                  multimedia: ['imagen_press_militar.jpg', 'video_press_militar.mp4'],
                  id: 11 }
            },
            {
                series: 2,
                repeticiones: 12,
                duracionMinutos: 6,
                ejercicio: {
                  nombre: 'Press militar',
                  descripcion: 'Ejercicio de press militar',
                  observaciones: 'Mantener la espalda recta',
                  tipo: 'Brazos',
                  musculos_trabajados: 'Hombros, tríceps',
                  material: 'Barra, discos',
                  dificultad: 'Intermedia',
                  multimedia: ['imagen_press_militar.jpg', 'video_press_militar.mp4'],
                  id: 11
                 }
            },
            // Más ejercicios aquí
        ],
        id: 1
    }
];

  ejercicios!: Ejercicio[];
  
  constructor(private modalService: NgbModal, private ejerciciosService: EjerciciosService) {}

  ngOnInit(): void {
    this.rutinas = this.rutinas;
  }

  // Imprime los detalles de una rutina
  mostrarDetalles(rutina: Rutina): void {
    const modalRef = this.modalService.open(DetallesRutina);
    modalRef.componentInstance.rutina = rutina;
  }

  // Abre el modal para editar una rutina ya existente
  editarRutina(rutina: Rutina): void {
    const modalRef = this.modalService.open(EditarRutina);
    modalRef.componentInstance.rutina = rutina;
    modalRef.componentInstance.accion = "Editar";
    modalRef.result.then((r: any) => {
      let indice = this.rutinas.findIndex(c => c.nombre == r.nombre);
      this.rutinas[indice] = r;
    }, (reason) => {});
  }

  // Se llama a esta función al pulsar el botón 'borrar'
  eliminarRutina(rut: Rutina) {
    let indice = this.rutinas.findIndex(c => c.id == rut.id);
    this.rutinas.splice(indice, 1);
  }

  // Abre modal para crear nueva rutina
  crearRutina(){
    const modalRef = this.modalService.open(CrearRutina);
    modalRef.componentInstance.accion = "Crear";
    modalRef.result.then((r: Rutina) => {
      this.rutinas.push(r);
    }, (reason) => {});
    
  }
}
