import {Component, OnInit} from "@angular/core";
import {PhotosService} from "../services/photos.service";
import {PhotoInfo} from "../entity/photo-info";

@Component({
    templateUrl: './view-page.component.html'
})
export class ViewPageComponent implements OnInit {

    public photoUrl: string = this.photoService.BASE_URL + "photo";

    private photoList: PhotoInfo[] = [];

    constructor(private photoService: PhotosService) {
    }

    ngOnInit() {
        this.photoService.getPhotos().subscribe((photoList) => {
            this.photoList = photoList;
        });
    }
}
