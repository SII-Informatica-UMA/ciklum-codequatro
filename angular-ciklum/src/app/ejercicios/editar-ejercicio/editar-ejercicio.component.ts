import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";
import { FormsModule } from "@angular/forms";

@Component({
    selector: 'app-editar-ejercicio',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './editar-ejercicio.component.html',
    styleUrl: './editar-ejercicio.component.css'
})
export class EditarEjercicio {
    @Input() ejercicio: any;
    nuevoEjercicio: any = {nombre: '', descripcion: '', observaciones: '', tipo: '', musculos_trabajados: '', material: '', dificultad: '', multimedia: []};
    accion?: "Añadir";

    constructor(public activeModal: NgbActiveModal) {}
    
    editarEjercicio() {
        this.activeModal.close(this.ejercicio);
    }
}