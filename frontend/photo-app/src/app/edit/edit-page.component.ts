import {Component, ElementRef, ViewChild} from "@angular/core";
import {PhotosService} from "../services/photos.service";
import {Photo} from "../entity/photo";
import {PhotoRenderInfo} from "../entity/photo-render-info";

@Component({
    templateUrl: './edit-page.component.html'
})
export class EditPageComponent {

    public photoList: PhotoRenderInfo[] = [];

    @ViewChild('choosedFilesBtn')
    public choosedFilesBtn: ElementRef;

    constructor(private photoService: PhotosService) {
    }

    /**
     * Selection files handler
     * @param value
     */
    onChoosePhotos(value: any): void {
        let fileList: FileList = value.target.files;
        for (let i = 0; i < fileList.length; i++) {
            let photo = new PhotoRenderInfo();
            photo.file = fileList.item(i);
            let reader = new FileReader();
            reader.onload = (event) => {
                let url = (event.target as FileReader).result;
                photo.url = url;
                // to get information about image size and count height
                let image = new Image();
                let newWidth = 100;
                let newHeight = image.height * (newWidth / image.width);
                image.src = url;
                image.onload = () => {
                    newHeight = image.height * (newWidth / image.width);
                };
                photo.width = newWidth;
                photo.height = newHeight;
            };
            this.photoList.push(photo);
            reader.readAsDataURL(photo.file);
        }
        this.choosedFilesBtn.nativeElement.value = "";
    }

    /**
     * Upload choosed photos to server
     */
    uploadPhotos() {
        let photos: Photo[] = this.photoList.filter((photo) => photo.choosen)
            .map(photo => new Photo(photo.file, photo.description));
        if (photos.length === 0) {
            return;
        }
        this.photoService.savePhotos(photos).subscribe((result) => {
                if (result) {
                    this.photoList = this.photoList.filter(photo => !photo.choosen);
                }
            }
        );
    }
}