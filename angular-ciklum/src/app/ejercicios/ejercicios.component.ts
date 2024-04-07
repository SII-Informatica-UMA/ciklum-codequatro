import { Component } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Ejercicio } from '../ejercicio';
import { DetallesEjercicio } from "./detalles-ejercicio/detalles.ejercicio.component";


import { EditarEjercicio } from "./editar-ejercicio/editar-ejercicio.component";
import { FormsModule } from '@angular/forms';



@Component({
  selector: 'app-ejercicios',
  standalone: true,
  imports: [FormsModule],
 templateUrl: './ejercicios.component.html',
  styleUrl: './ejercicios.component.css'
})
export class EjercicioComponent{
    id = 11;
    ejercicios: Ejercicio[] = [
        { 
          nombre: 'Prensa',
          descripcion: 'Ejercicio de prensa',
          observaciones: 'Cuidado con la postura',
          tipo: 'Tren inferior',
          musculos_trabajados: 'Cuádriceps, glúteos, isquiotibiales',
          material: 'Máquina de prensa',
          dificultad: 'Intermedia',
          multimedia: ['../../../assets/imagenes_ejercicios/prensa.jpg', 'https://www.youtube.com/embed/xvCynwyNoP4?si=88naJYgYUTuZa8IN'],
          id: 0
        },
        { 
          nombre: 'Sentadilla Bulgara',
          descripcion: 'Ejercicio de sentadilla búlgara',
          observaciones: 'Mantener el equilibrio',
          tipo: 'Tren inferior',
          musculos_trabajados: 'Cuádriceps, glúteos',
          material: 'Barra, pesas',
          dificultad: 'Intermedia',
          multimedia: ['../../../assets/imagenes_ejercicios/sentadilla-bulgara.jpg', 'https://www.youtube.com/embed/hbMgXooX2zo'],
          id: 1
        },
        { 
          nombre: 'Extension de cuadriceps',
          descripcion: 'Ejercicio de extensión de cuádriceps',
          observaciones: 'Controlar el movimiento',
          tipo: 'Tren inferior',
          musculos_trabajados: 'Cuádriceps',
          material: 'Máquina de cuádriceps',
          dificultad: 'Baja',
          multimedia: ['../../../assets/imagenes_ejercicios/extension-de-cuadriceps.gif', 'https://www.youtube.com/embed/r7ZMTzfiICA?si=E1t74aFlOCERKDXL'],
          id: 2
        },
        { 
          nombre: 'Extensión de isquiotibiales',
          descripcion: 'Ejercicio de extensión de isquiotibiales',
          observaciones: 'Mantener la espalda recta',
          tipo: 'Tren inferior',
          musculos_trabajados: 'Isquiotibiales',
          material: 'Máquina de isquiotibiales',
          dificultad: 'Baja',
          multimedia: ['../../../assets/imagenes_ejercicios/extension-de-isquiotibiales.jpeg', 'https://www.youtube.com/embed/5X3NqbieXhg?si=s7qGc_e31bJPBtY8'],
          id: 3
        },
        { 
          nombre: 'Bench press',
          descripcion: 'Ejercicio de press de banca',
          observaciones: 'Utilizar un agarre adecuado',
          tipo: 'Tren superior',
          musculos_trabajados: 'Pectorales, tríceps, deltoides',
          material: 'Banco de press, barra y discos',
          dificultad: 'Intermedia',
          multimedia: ['../../../assets/imagenes_ejercicios/bench-press.gif', 'https://www.youtube.com/embed/SCVCLChPQFY?si=fhCajzCQmf3Q1dZm'],
          id: 4
        },
        { 
          nombre: 'Fly press',
          descripcion: 'Ejercicio de press de mosca',
          observaciones: 'Controlar el movimiento descendente',
          tipo: 'Tren superior',
          musculos_trabajados: 'Pectorales',
          material: 'Máquina de fly press',
          dificultad: 'Intermedia',
          multimedia: ['../../../assets/imagenes_ejercicios/fly-press.jpg', 'https://www.youtube.com/embed/x2mfixsQW9s?si=cJIVJY-BjSyR2hKQ'],
          id: 5
        },
        { 
          nombre: 'Back',
          descripcion: 'Ejercicio de remo',
          observaciones: 'Mantener la espalda recta',
          tipo: 'Tren superior',
          musculos_trabajados: 'Dorsales, bíceps',
          material: 'Barra, mancuernas',
          dificultad: 'Intermedia',
          multimedia: ['../../../assets/imagenes_ejercicios/back.gif', 'https://www.youtube.com/embed/x2Y6Mb41zjY?si=EZlHSr5Av1b5jSVn'],
          id: 6
        },
        { 
          nombre: 'Pull over',
          descripcion: 'Ejercicio de pull over',
          observaciones: 'Controlar el movimiento',
          tipo: 'Tren superior',
          musculos_trabajados: 'Pectorales, dorsal ancho',
          material: 'Máquina de pull over, mancuerna',
          dificultad: 'Intermedia',
          multimedia: ['../../../assets/imagenes_ejercicios/pull-over.jpg', 'https://www.youtube.com/embed/i0ctwS0wD4Y?si=jsX0rrWI6kjrdTpR'],
          id: 7
        },
        { 
          nombre: 'Triceps',
          descripcion: 'Ejercicio de extensión de tríceps',
          observaciones: 'Mantener los codos estables',
          tipo: 'Brazos',
          musculos_trabajados: 'Tríceps',
          material: 'Máquina de tríceps, mancuerna',
          dificultad: 'Intermedia',
          multimedia: ['../../../assets/imagenes_ejercicios/triceps.jpg', 'https://www.youtube.com/embed/dRkTreltpnc?si=1zW1jsRGMzoEAvGs'],
          id: 8
        },
        { 
          nombre: 'Biceps con barra Z',
          descripcion: 'Ejercicio de curl de bíceps con barra Z',
          observaciones: 'Controlar el movimiento',
          tipo: 'Brazos',
          musculos_trabajados: 'Bíceps',
          material: 'Barra Z, discos',
          dificultad: 'Intermedia',
          multimedia: ['../../../assets/imagenes_ejercicios/biceps-con-barra-z.jpg', 'https://www.youtube.com/embed/0l5EiITOB9E?si=es4d9LIP_XxKBdsZ'],
          id: 9
        },
        { 
          nombre: 'Biceps martillo',
          descripcion: 'Ejercicio de curl de bíceps martillo',
          observaciones: 'Mantener los codos cerca del cuerpo',
          tipo: 'Brazos',
          musculos_trabajados: 'Bíceps, antebrazo',
          material: 'Mancuernas',
          dificultad: 'Intermedia',
          multimedia: ['../../../assets/imagenes_ejercicios/biceps-martillo.gif', 'https://www.youtube.com/embed/de4AdSz4gcU?si=miy1WP0JKPvVPtA5'],
          id: 10
        },
        { 
          nombre: 'Press militar',
          descripcion: 'Ejercicio de press militar',
          observaciones: 'Mantener la espalda recta',
          tipo: 'Brazos',
          musculos_trabajados: 'Hombros, tríceps',
          material: 'Barra, discos',
          dificultad: 'Intermedia',
          multimedia: ['../../../assets/imagenes_ejercicios/press-militar.gif', 'https://www.youtube.com/embed/o5M9RZ-vWrc?si=sAxhQu1dKBxF7vsq'],
          id: 11
        },
      ];
      nuevoEjercicio: Ejercicio = {
        nombre: '',
        descripcion: '',
        observaciones: '',
        tipo: '',
        musculos_trabajados: '',
        material: '',
        dificultad: '',
        multimedia: [],
        id : this.id
      };

      ejercicioSeleccionado: any = null;

      editarEjercicioIndex: number = -1;

      constructor(private modalService: NgbModal){ 
      }

      agregarEjercicio(){
        let ref = this.modalService.open(EditarEjercicio);
        ref.componentInstance.ejercicio = {...this.nuevoEjercicio}; // Pasar el nuevo ejercicio al modal
        ref.result.then((nuevoEjercicio: Ejercicio) => {
          if (nuevoEjercicio) {
            this.ejercicios.push(nuevoEjercicio); // Agregar el nuevo ejercicio al arreglo
          }
          // Restablecer el nuevo ejercicio
          this.nuevoEjercicio = {
            nombre: '',
            descripcion: '',
            observaciones: '',
            tipo: '',
            musculos_trabajados: '',
            material: '',
            dificultad: '',
            multimedia: [],
            id: this.id++
          };
        }, () => {}); // Manejar el caso en que el usuario cancele la operación
      }
      

      mostrarDetalles(ejercicio: Ejercicio): void{
        const modalRef = this.modalService.open(DetallesEjercicio)
        modalRef.componentInstance.ejercicio = ejercicio
      }

      editarEjercicio(ejercicio: any): void{
       const modalRef = this.modalService.open(EditarEjercicio);
        modalRef.componentInstance.ejercicio = ejercicio;
        modalRef.componentInstance.accion = "Editar";
        modalRef.result.then((r: any) => {
          let indice = this.ejercicios.findIndex(x => x.nombre == r.nombre);
          this.ejercicios[indice] = r;
      }, (reason: any) => {});
      }
      

      actualizarEjercicio(){
        this.ejercicios[this.editarEjercicioIndex] = {...this.nuevoEjercicio};
        this.editarEjercicioIndex = -1;
        this.nuevoEjercicio
        this.nuevoEjercicio = {
          nombre: '',
          descripcion: '',
          observaciones: '',
          tipo: '',
          musculos_trabajados: '',
          material: '',
          dificultad: '',
          multimedia: [],
          id: 0
        }
      }

      eliminarEjercicio(nombre:String){
        let indice = this.ejercicios.findIndex(x => x.nombre == nombre);
        this.ejercicios.splice(indice,1);
      }
   
}
