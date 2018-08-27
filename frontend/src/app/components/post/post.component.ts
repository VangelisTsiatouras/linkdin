import { Component, OnInit, Input } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css'],
})
export class PostComponent implements OnInit {

  userId = localStorage.getItem('userID');
  userToken = localStorage.getItem('userToken');

  userName: string;
  userSurname: string;
  userImage: string;
  @Input() postId: string;
  @Input() userIdPost: string;
  @Input() date: Date;
  @Input() isAd: any;
  @Input() isPublic: any;
  @Input() content: string;

  // Interests
  interestedUsersIDs = [];
  interestedUsers = [];
  isInterested: boolean;
  numberOfInterests: any;

  // Comments
  showComments = false;
  comment: string;
  comments = [];
  numberOfComments: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.getUserIdentifiers();
    this.getInterests();
    this.getCommentsNumber();
  }

  getUserIdentifiers() {
    const userIdentifiers = { userToken: this.userToken, id: this.userId };
    const userInfoRequest = { userIdInfo: this.userIdPost };
    const API_URL = environment.API_URL;
    const req = this.http.post(API_URL + '/api/getuserbasicinfo', {
      userIdentifiers,
      userInfoRequest
    }, { responseType: 'text', withCredentials: true }).subscribe((data: any) => {
      const obj = JSON.parse(data);
      this.userName = obj.name;
      this.userSurname = obj.surname;
      this.userImage = 'data:image/jpeg;base64,' + obj.image;
      console.log('here');
    },
      (err: HttpErrorResponse) => {
        console.log(err);
      });
  }

  interested() {
    const userIdentifiers = { userToken: this.userToken, id: this.userId };
    const interest = { postID: this.postId, isInterested: '1' };
    const API_URL = environment.API_URL;
    const req = this.http.post(API_URL + '/api/interest', {
      userIdentifiers,
      interest
    }, { responseType: 'text', withCredentials: true }).subscribe((data: any) => {
      this.getInterests();
    },
      (err: HttpErrorResponse) => {
        console.log(err);
      });
  }

  getInterests() {
    const userIdentifiers = { userToken: this.userToken, id: this.userId };
    const interestedUsers = { postID: this.postId };
    const API_URL = environment.API_URL;
    const req = this.http.post(API_URL + '/api/interestsdata', {
      userIdentifiers,
      interestedUsers
    }, { responseType: 'text', withCredentials: true }).subscribe((data: any) => {
      const obj = JSON.parse(data);
      this.numberOfInterests = obj.numberOfInterestedUsers;
      this.isInterested = obj.isUserInterested;
      this.interestedUsersIDs = obj.interestedUsers;
      console.log(this.numberOfInterests);
    },
      (err: HttpErrorResponse) => {
        console.log(err);
      });
  }

  getInterestedUsers() {
    this.interestedUsers = [];
    const userIdentifiers = { userToken: this.userToken, id: this.userId };
    const interestedUsers = { postID: this.postId };
    const API_URL = environment.API_URL;
    const req = this.http.post(API_URL + '/api/interestedusersinfo', {
      userIdentifiers,
      interestedUsers
    }, { responseType: 'text', withCredentials: true }).subscribe((data: any) => {
      const obj = JSON.parse(data);
      const numberOfInterests = obj.numberOfResults;
      this.interestedUsers = obj.list;
      for (let i = 0; i < numberOfInterests; i++) {
        this.interestedUsers[i].image = 'data:image/jpeg;base64,' + this.interestedUsers[i].image;
      }
      console.log(this.interestedUsers);
    },
      (err: HttpErrorResponse) => {
        console.log(err);
      });
  }

  getCommentsNumber() {
    const userIdentifiers = { userToken: this.userToken, id: this.userId };
    const commentData = { postID: this.postId };
    const API_URL = environment.API_URL;
    const req = this.http.post(API_URL + '/api/gettotalcomments', {
      userIdentifiers,
      commentData
    }, { responseType: 'text', withCredentials: true }).subscribe((data: any) => {
      const obj = JSON.parse(data);
      this.numberOfComments = obj;
    },
      (err: HttpErrorResponse) => {
        console.log(err);
      });
  }

  loadComments() {
    if (this.comments.length > 0) {
      this.showComments = !this.showComments;
    } else {
      const userIdentifiers = { userToken: this.userToken, id: this.userId };
      const commentData = { postID: this.postId };
      const API_URL = environment.API_URL;
      const req = this.http.post(API_URL + '/api/getcomments', {
        userIdentifiers,
        commentData
      }, { responseType: 'text', withCredentials: true }).subscribe((data: any) => {
        const obj = JSON.parse(data);
        this.comments = obj;
        for (let i = 0; i < this.comments.length; i++) {
          this.comments[i].image = 'data:image/jpeg;base64,' + this.comments[i].image;
        }
        this.showComments = true;
        console.log(obj);
      },
        (err: HttpErrorResponse) => {
          console.log(err);
        });
    }
  }

  postComment() {
    const userIdentifiers = { userToken: this.userToken, id: this.userId };
    const commentData = { postID: this.postId, comment: this.comment };
    const API_URL = environment.API_URL;
    const req = this.http.post(API_URL + '/api/postcomment', {
      userIdentifiers,
      commentData
    }, { responseType: 'text', withCredentials: true }).subscribe((data: any) => {
      this.comment = '';
      this.ngOnInit();
      this.loadComments();
    },
      (err: HttpErrorResponse) => {
        console.log(err);
      });
  }

}
