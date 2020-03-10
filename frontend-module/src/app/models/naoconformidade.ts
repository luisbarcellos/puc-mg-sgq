import { RiscoAcidente } from './riscoacidente';
import { Problema } from './problema';

export interface NaoConformidade {
    idNaoConformidade: number;
    tipo: string;
    descricao: string;
    riscoAcidente: RiscoAcidente;
    problema: Problema;
}