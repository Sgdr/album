import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {Photo} from "../entity/photo";
import {PhotoInfo} from "../entity/photo-info";

@Injectable()
export class PhotosService {

    //TODO inject BASE_URL with InjectionToken (now can'n import InjectionToken)
    public BASE_URL: string = "http://localhost:8080/album/";
    private SAVE_URL: string = this.BASE_URL + "save";
    private GET_PHOTOS_URL: string =  this.BASE_URL + "photos";

    constructor(private http: Http) {
    }

    public savePhotos(photoList: Photo[]): Observable<boolean> {
        let formData: FormData = new FormData();
        let descriptions: PhotoInfo[] = photoList.filter(photo => photo.description)
            .map(photo => new PhotoInfo(photo.file.name, photo.description));
        let blobProperty: BlobPropertyBag = {type: 'application/json'};
        formData.append("descriptions", new Blob([JSON.stringify(descriptions)], blobProperty));
        for (let i = 0; i < photoList.length; i++) {
            formData.append("photosUpload", photoList[i].file, photoList[i].file.name);
        }
        return this.http.post(this.SAVE_URL, formData).map((res: Response) => {
            return true;
        }).catch((error) => {
            return Observable.of(false);
        })
    }

    public getPhotos(): Observable<PhotoInfo[]> {
        return this.http.get(this.GET_PHOTOS_URL)
            .map((res:Response) => this.extractResponse(res))
            .catch((error) => {
            return Observable.empty();
        });
    }

    /**
     * Get Json object from response
     * @param {Response} res
     * @returns {T}
     */
    private extractResponse<T>(res: Response): T {
        if (res.status < 200 || res.status >= 300) {
            throw new Error('Bad response status: ' + res.status);
        }
        let body = {};
        // no content - json в нем нет
        if (res.status != 204) {
            body = res.json() || {};
        }
        return <T>body;
    }
}
