import {Photo} from "./photo";

/**
 * photos information to render on page
 */
export class PhotoRenderInfo extends Photo {

    url: string;
    choosen: boolean;
    width: number;
    height: number;
}