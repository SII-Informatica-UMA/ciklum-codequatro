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
                  multimedia: ['../../../assets/imagenes_ejercicios/press-militar.gif', 'https://www.youtube.com/embed/o5M9RZ-vWrc?si=sAxhQu1dKBxF7vsq'],
                  id: 11 }
            },
            {
                series: 2,
                repeticiones: 12,
                duracionMinutos: 6,
                ejercicio: {
                  nombre: 'Sentadilla Bulgara',
                  descripcion: 'Ejercicio de sentadilla búlgara',
                  observaciones: 'Mantener el equilibrio',
                  tipo: 'Tren inferior',
                  musculos_trabajados: 'Cuádriceps, glúteos',
                  material: 'Barra, pesas',
                  dificultad: 'Intermedia',
                  multimedia: ['../../../assets/imagenes_ejercicios/sentadilla-bulgara.jpg', 'https://www.youtube.com/embed/hbMgXooX2zo'],
                  id: 1
                 }
            },
            // Más ejercicios aquí
        ],
        id: 1
    },
    {
      nombre: 'Rutina de fuerza',
      descripcion: 'Rutina diseñada para desarrollar fuerza muscular en todo el cuerpo.',
      observaciones: 'Realizar con buena técnica y descansar lo necesario entre series.',
      ejercicios: [
          {
              series: 3,
              repeticiones: 8,
              duracionMinutos: 10,
              ejercicio: {
                  nombre: 'Prensa',
                  descripcion: 'Ejercicio de prensa',
                  observaciones: 'Mantener el abdomen contraído',
                  tipo: 'Tren inferior',
                  musculos_trabajados: 'Piernas, glúteos',
                  material: 'Máquina de prensa',
                  dificultad: 'Intermedia',
                  multimedia: ['../../../assets/imagenes_ejercicios/prensa.jpg', 'https://www.youtube.com/embed/xvCynwyNoP4?si=88naJYgYUTuZa8IN'],
                  id: 1
              }
          },
          // Más ejercicios aquí
      ],
      id: 1
  },
  
  {
      nombre: 'Rutina de piernas',
      descripcion: 'Rutina enfocada en el desarrollo de piernas y glúteos.',
      observaciones: 'Realizar cada ejercicio con amplitud de movimiento controlada.',
      ejercicios: [
          {
              series: 4,
              repeticiones: 12,
              duracionMinutos: 15,
              ejercicio: {
                  nombre: 'Sentadilla búlgara',
                  descripcion: 'Ejercicio de sentadilla búlgara',
                  observaciones: 'Mantener el equilibrio',
                  tipo: 'Tren inferior',
                  musculos_trabajados: 'Cuádriceps, glúteos',
                  material: 'Barra, pesas',
                  dificultad: 'Intermedia',
                  multimedia: ['../../../assets/imagenes_ejercicios/sentadilla-bulgara.jpg', 'https://www.youtube.com/embed/hbMgXooX2zo'],
                  id: 2
              }
          },
          // Más ejercicios aquí
      ],
      id: 3
  },
  
  {
      nombre: 'Rutina de espalda y pecho',
      descripcion: 'Rutina diseñada para fortalecer la espalda y el pecho.',
      observaciones: 'Mantener la espalda recta en todos los ejercicios de espalda.',
      ejercicios: [
          {
              series: 3,
              repeticiones: 10,
              duracionMinutos: 12,
              ejercicio: {
                  nombre: 'Bench press',
                  descripcion: 'Ejercicio de bench press',
                  observaciones: 'Mantener los codos estables',
                  tipo: 'Pecho',
                  musculos_trabajados: 'Pectorales, tríceps',
                  material: 'Barra, discos',
                  dificultad: 'Intermedia',
                  multimedia: ['../../../assets/imagenes_ejercicios/bench-press.gif', 'https://www.youtube.com/embed/SCVCLChPQFY?si=fhCajzCQmf3Q1dZm'],
                  id: 3
              }
          },
          // Más ejercicios aquí
      ],
      id: 4
  },
  
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
    })
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
      console.log(r);
      this.rutinas.push(r);
    }, (reason) => {
      console.log('No se creó una nueva rutina');
    });
    
  }
}
