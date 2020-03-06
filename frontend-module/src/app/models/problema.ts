import { Incidente } from './incidente';

export interface Problema {
    idProblema: number;
    tipo: string;
    gravidade: string;
    descricao: string;
    dataInclusao: string;
    incidentes: Incidente[];
}