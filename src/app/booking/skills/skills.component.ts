import { Component, OnInit } from '@angular/core';
import { Skill } from './Skill';
import { SkillService } from './skill-service';
import { ActivatedRoute } from '@angular/router';
import { WorkerList } from '../worker-list/worker-list';
import { WorkerListReal } from '../worker-list/worker-list-real';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss'],
  providers: [SkillService]
})
export class StoSkillComponent implements OnInit {
  workerList : WorkerList = new WorkerListReal();
  myskill : Map<string, number> = new Map();
  timeValue : number = 0;
  skills : Array<Skill>;
  error: ErrorEvent;
  searchId : number;

  show : boolean = true;

  constructor(private skillService: SkillService, private route: ActivatedRoute) {}
  ngOnInit() {
     this.route.params.subscribe(params => {
     this.searchId = params["id"];
   });
     this.skillService.getAllSkillsToStoResp(this.searchId)
     .subscribe((data : Skill[])=> this.skills = data,
      error => this.error = error);
  }



  addOrDelete(s : Skill){
    this.show = true;

    if(this.myskill.get(s.name) != undefined){
      if(this.myskill.get(s.name) == -1){
        this.timeValue += s.requiredTime;
        this.myskill.set(s.name, 1);
        this.setClass(s.id, 'selected');
      }
      else{
        this.timeValue -= s.requiredTime;
        this.myskill.set(s.name, -1);
        this.setClass(s.id, 'non-select');
      }
  }
  else{
    this.myskill.set(s.name, 1);
    this.timeValue += s.requiredTime;
    this.setClass(s.id, 'selected');
  }
    
  }

  private setClass(id: number, className: string): void{
    document.getElementById(String(id)).className = "jumbotron text-center hoverable p-4 " + className;
  }

  changeShow(){
    this.workerList = this.GetData();
    this.show = false;
  }

  GetData() : WorkerList {
   let workerList : WorkerList = new WorkerListReal();
   let skillName : string[] = new Array<string>();
   this.myskill.forEach((value: number, key: string) => {
    if(value === 1){
      skillName.push(key);
    }
});
    workerList.name = skillName;
    workerList.searchId = this.searchId;

    return workerList;
  }
}
