import { Ejercicio } from "./ejercicio";

export interface Rutina{
    nombre: String,
    descripcion: String,
    observaciones: String,
    ejercicios: {
        series: number,
        repeticiones: number,
        duracionMinutos: number,
        ejercicio: Ejercicio
    }[],
    id: number
}



