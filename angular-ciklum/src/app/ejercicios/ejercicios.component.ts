import { Component } from '@angular/core';
import { ModalService } from '@developer-partners/ngx-modal-dialog';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Ejercicio } from '../ejercicio';
import { DetallesEjercicio } from "./detalles-ejercicio/detalles.ejercicio.component";


@Component({
  selector: 'app-ejercicios',
  standalone: true,
  imports: [],
  templateUrl: './ejercicios.component.html',
  styleUrl: './ejercicios.component.css'
})
export class EjercicioComponent{
    ejercicios: Ejercicio[] = [
        { 
          nombre: 'Prensa',
          descripcion: 'Ejercicio de prensa',
          observaciones: 'Cuidado con la postura',
          tipo: 'Tren inferior',
          musculos_trabajados: 'Cuádriceps, glúteos, isquiotibiales',
          material: 'Máquina de prensa',
          dificultad: 'Intermedia',
          multimedia: ['imagen_prensa.jpg', 'video_prensa.mp4'],
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
          multimedia: ['imagen_sentadilla_bulgara.jpg', 'video_sentadilla_bulgara.mp4'],
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
          multimedia: ['imagen_extension_cuadriceps.jpg', 'video_extension_cuadriceps.mp4'],
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
          multimedia: ['imagen_extension_isquiotibiales.jpg', 'video_extension_isquiotibiales.mp4'],
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
          multimedia: ['imagen_bench_press.jpg', 'video_bench_press.mp4'],
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
          multimedia: ['imagen_fly_press.jpg', 'video_fly_press.mp4'],
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
          multimedia: ['imagen_back.jpg', 'video_back.mp4'],
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
          multimedia: ['imagen_pull_over.jpg', 'video_pull_over.mp4'],
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
          multimedia: ['imagen_triceps.jpg', 'video_triceps.mp4'],
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
          multimedia: ['imagen_biceps_barra_z.jpg', 'video_biceps_barra_z.mp4'],
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
          multimedia: ['imagen_biceps_martillo.jpg', 'video_biceps_martillo.mp4'],
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
          multimedia: ['imagen_press_militar.jpg', 'video_press_militar.mp4'],
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
        id: 0
      };

      ejercicioSeleccionado: any = null;

      editarEjercicioIndex: number = -1;

      constructor(private modalService: NgbModal){ 
      }

      agregarEjercicio(){
        this.ejercicios.push({...this.nuevoEjercicio})
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

      mostrarDetalles(ejercicio: Ejercicio): void{
        const modalRef = this.modalService.open(DetallesEjercicio)
        modalRef.componentInstance.ejercicio = ejercicio
      }

      editarEjercicio(index: number){
        this.editarEjercicioIndex = index
        this.nuevoEjercicio = {...this.ejercicios[index]}
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
