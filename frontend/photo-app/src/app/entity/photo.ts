/**
 * Photo information to upload
 */
export class Photo {

    constructor(file?: File, description?: string) {
        this.file = file;
        this.description = description;
    }

    description: string;
    file: File;
}
