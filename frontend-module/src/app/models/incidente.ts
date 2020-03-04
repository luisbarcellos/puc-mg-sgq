import { Produto } from './produto';

export interface Incidente {
    idIncidente: number;
    tipo: string;
    gravidade: string;
    descricao: string;
    dataInclusao: string;
    produtos: Produto[];
}