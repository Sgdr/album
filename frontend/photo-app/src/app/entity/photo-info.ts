/**
 * Photo's information
 */
export class PhotoInfo {

    imageName: string;
    description: string;

    constructor(imageName: string, description: string) {
        this.imageName = imageName;
        this.description = description;
    }
}