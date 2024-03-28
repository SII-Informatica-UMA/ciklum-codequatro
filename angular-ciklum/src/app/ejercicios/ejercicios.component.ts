import { Component } from '@angular/core';

@Component({
  selector: 'app-ejercicios',
  standalone: true,
  imports: [],
  templateUrl: './ejercicios.component.html',
  styleUrl: './ejercicios.component.css'
})
export class EjerciciosComponent {
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
