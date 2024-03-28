import { Component } from '@angular/core';
import { ModalService } from '@developer-partners/ngx-modal-dialog';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Ejercicios } from '../../ejercicio';

@Component({
  selector: 'app-ejercicios',
  standalone: true,
  imports: [],
  templateUrl: './ejercicios.component.html',
  styleUrl: './ejercicios.component.css'
})
export class EjerciciosComponent {
  private ejercicios: Ejercicios[] = [
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
  mostrarInformacion(idBoton: number): void {
    let nombre = "";
    let descripcion = "";
    let observaciones = "";
    let tipo = "";
    let musculosTrabajados = "";
    let material = "";
    let dificultad = "";
    let multimedia: string[] = [];

    switch (idBoton) {
        case 0:
            nombre = "Prensa";
            descripcion = "Descripción del ejercicio de prensa.";
            observaciones = "Observaciones sobre el ejercicio de prensa.";
            tipo = "Tipo de ejercicio de prensa.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de prensa.";
            material = "Material necesario para realizar el ejercicio de prensa.";
            dificultad = "Dificultad del ejercicio de prensa.";
            multimedia = ["imagen1.jpg", "video1.mp4"];
            break;
        case 1:
            nombre = "Sentadilla Bulgara";
            descripcion = "Descripción del ejercicio de sentadilla búlgara.";
            observaciones = "Observaciones sobre el ejercicio de sentadilla búlgara.";
            tipo = "Tipo de ejercicio de sentadilla búlgara.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de sentadilla búlgara.";
            material = "Material necesario para realizar el ejercicio de sentadilla búlgara.";
            dificultad = "Dificultad del ejercicio de sentadilla búlgara.";
            multimedia = ["imagen2.jpg", "video2.mp4"];
            break;
        case 2:
            nombre = "Extension de cuadriceps";
            descripcion = "Descripción del ejercicio de extensión de cuádriceps.";
            observaciones = "Observaciones sobre el ejercicio de extensión de cuádriceps.";
            tipo = "Tipo de ejercicio de extensión de cuádriceps.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de extensión de cuádriceps.";
            material = "Material necesario para realizar el ejercicio de extensión de cuádriceps.";
            dificultad = "Dificultad del ejercicio de extensión de cuádriceps.";
            multimedia = ["imagen3.jpg", "video3.mp4"];
            break;
        case 3:
            nombre = "Extensión de isquiotibiales";
            descripcion = "Descripción del ejercicio de extensión de isquiotibiales.";
            observaciones = "Observaciones sobre el ejercicio de extensión de isquiotibiales.";
            tipo = "Tipo de ejercicio de extensión de isquiotibiales.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de extensión de isquiotibiales.";
            material = "Material necesario para realizar el ejercicio de extensión de isquiotibiales.";
            dificultad = "Dificultad del ejercicio de extensión de isquiotibiales.";
            multimedia = ["imagen4.jpg", "video4.mp4"];
            break;
        case 4:
            nombre = "Bench press";
            descripcion = "Descripción del ejercicio de bench press.";
            observaciones = "Observaciones sobre el ejercicio de bench press.";
            tipo = "Tipo de ejercicio de bench press.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de bench press.";
            material = "Material necesario para realizar el ejercicio de bench press.";
            dificultad = "Dificultad del ejercicio de bench press.";
            multimedia = ["imagen5.jpg", "video5.mp4"];
            break;
        case 5:
            nombre = "Fly press";
            descripcion = "Descripción del ejercicio de fly press.";
            observaciones = "Observaciones sobre el ejercicio de fly press.";
            tipo = "Tipo de ejercicio de fly press.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de fly press.";
            material = "Material necesario para realizar el ejercicio de fly press.";
            dificultad = "Dificultad del ejercicio de fly press.";
            multimedia = ["imagen6.jpg", "video6.mp4"];
            break;
        case 6:
            nombre = "Back";
            descripcion = "Descripción del ejercicio de back.";
            observaciones = "Observaciones sobre el ejercicio de back.";
            tipo = "Tipo de ejercicio de back.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de back.";
            material = "Material necesario para realizar el ejercicio de back.";
            dificultad = "Dificultad del ejercicio de back.";
            multimedia = ["imagen7.jpg", "video7.mp4"];
            break;
        case 7:
            nombre = "Pull over";
            descripcion = "Descripción del ejercicio de pull over.";
            observaciones = "Observaciones sobre el ejercicio de pull over.";
            tipo = "Tipo de ejercicio de pull over.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de pull over.";
            material = "Material necesario para realizar el ejercicio de pull over.";
            dificultad = "Dificultad del ejercicio de pull over.";
            multimedia = ["imagen8.jpg", "video8.mp4"];
            break;
        case 8:
            nombre = "Triceps";
            descripcion = "Descripción del ejercicio de triceps.";
            observaciones = "Observaciones sobre el ejercicio de triceps.";
            tipo = "Tipo de ejercicio de triceps.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de triceps.";
            material = "Material necesario para realizar el ejercicio de triceps.";
            dificultad = "Dificultad del ejercicio de triceps.";
            multimedia = ["imagen9.jpg", "video9.mp4"];
            break;
        case 9:
            nombre = "Biceps con barra Z";
            descripcion = "Descripción del ejercicio de biceps con barra Z.";
            observaciones = "Observaciones sobre el ejercicio de biceps con barra Z.";
            tipo = "Tipo de ejercicio de biceps con barra Z.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de biceps con barra Z.";
            material = "Material necesario para realizar el ejercicio de biceps con barra Z.";
            dificultad = "Dificultad del ejercicio de biceps con barra Z.";
            multimedia = ["imagen10.jpg", "video10.mp4"];
            break;
        case 10:
            nombre = "Biceps martillo";
            descripcion = "Descripción del ejercicio de biceps martillo.";
            observaciones = "Observaciones sobre el ejercicio de biceps martillo.";
            tipo = "Tipo de ejercicio de biceps martillo.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de biceps martillo.";
            material = "Material necesario para realizar el ejercicio de biceps martillo.";
            dificultad = "Dificultad del ejercicio de biceps martillo.";
            multimedia = ["imagen11.jpg", "video11.mp4"];
            break;
        case 11:
            nombre = "Press militar";
            descripcion = "Descripción del ejercicio de press militar.";
            observaciones = "Observaciones sobre el ejercicio de press militar.";
            tipo = "Tipo de ejercicio de press militar.";
            musculosTrabajados = "Músculos trabajados en el ejercicio de press militar.";
            material = "Material necesario para realizar el ejercicio de press militar.";
            dificultad = "Dificultad del ejercicio de press militar.";
            multimedia = ["imagen12.jpg", "video12.mp4"];
            break;
        default:
            alert('Ejercicio no reconocido');
            return; // Salir de la función si no se reconoce el ejercicio
    }

    alert(
        'Información del ejercicio: ' + '\n\n' +
        '\n' +  // espaciar un poco
        'Nombre: ' + nombre + '\n' +
        'Descripción: ' + descripcion + '\n' +
        'Observaciones: ' + observaciones + '\n' +
        'Tipo: ' + tipo + '\n' +
        'Músculos Trabajados: ' + musculosTrabajados + '\n' +
        'Material: ' + material + '\n' +
        'Dificultad: ' + dificultad + '\n' +
        'Multimedia: ' + JSON.stringify(multimedia) + '\n' +
        'ID: ' + idBoton
    );
    

  
  }


}
