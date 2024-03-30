import { Component } from '@angular/core';
import { Ejercicio } from '../../ejercicio';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-formulario-ejercicio',
  standalone: true,
  imports: [],
  templateUrl: './formulario-ejercicio.component.html',
  styleUrl: './formulario-ejercicio.component.css'
})
export class FormularioEjercicioComponent {
  accion? : "Añadir" | "Editar"
  ejercicio: Ejercicio = {nombre: '',
  descripcion : '',
  observaciones : '',
  musculos_trabajados : '',
  tipo: '',
  material : '',
  dificultad : '',
  multimedia : [''],
  id : 0
  }
  constructor(public modal: NgbActiveModal){}

  guardarEjercicio() : void {
    this.modal.close(this.ejercicio)
  }
}
