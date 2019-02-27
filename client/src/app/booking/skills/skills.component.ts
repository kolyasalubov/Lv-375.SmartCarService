import { Component, OnInit } from '@angular/core';
import { SkillService } from './skill-service';
import { ActivatedRoute } from '@angular/router';
import { WorkerList } from '../worker-list/worker-list-real';
import { NumberValueAccessor } from '@angular/forms/src/directives';
import { WorkType } from './work-type';
@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss'],
  providers: [SkillService]
})
export class StoSkillComponent implements OnInit {
  workerList : WorkerList = new WorkerList();
  selectedWork : Map<string, number> = new Map();
  totalPrice : number = 0;
  workType : Map<string, Array<WorkType>>;
  error: ErrorEvent;
  searchId : number;

  show : boolean = true;
  works : Array<WorkType>;
  headElements : Array<string> = ["Name", "Time", "Cost"];
  selectedSkill : Map<string, number> = new Map();
  lastSkillName : string;

  constructor(private skillService: SkillService, private route: ActivatedRoute) {}
  ngOnInit() {
     this.route.params.subscribe(params => {
     this.searchId = params["id"];
   });
     this.skillService.getAllSkillsToStoResp(this.searchId)
     .subscribe((data : Map<string, Array<WorkType>>)=> this.workType = data,
      error => this.error = error);
  }



  addOrDelete(w : WorkType, skillName : string){
    this.show = true;

    if(this.selectedWork.get(w.name) != undefined){
      if(this.selectedWork.get(w.name) == -1){
        this.totalPrice += w.cost;
        this.selectedWork.set(w.name, 1);
        this.setClass(w.name, 'selected');
        this.selectedSkill.set(skillName, this.selectedSkill.get(skillName) + 1);
      }
      else{
        this.totalPrice -= w.cost;
        this.selectedWork.set(w.name, -1);
        this.setClass(w.name, 'non-select');
        this.selectedSkill.set(skillName, this.selectedSkill.get(skillName) - 1);
      }
  }
  else{
    this.selectedWork.set(w.name, 1);
    this.totalPrice += w.cost;
    this.setClass(w.name, 'selected');
    if(this.selectedSkill.get(skillName)==undefined){
      this.selectedSkill.set(skillName, 1);
    }
    else{
      this.selectedSkill.set(skillName, this.selectedSkill.get(skillName) + 1);
    }
  }
    
  }

  private setClass(id: string, className: string): void{
    document.getElementById(id).className = className;
  }

  private setSkillClass(id: string, className: string): void{
    document.getElementById(id).className = "list-group-item " + className;
  }

  changeShow(){
    this.workerList = this.GetData();
    this.show = false;
  }

  GetData() : WorkerList {
    this.works = undefined;

   let workerList : WorkerList = new WorkerList();
   let workName : string[] = new Array<string>();
   let skillName : string[] = new Array<string>();
   this.selectedWork.forEach((value: number, key: string) => {
    if(value === 1){
      workName.push(key);
    }
});
this.selectedSkill.forEach((value: number, key: string) => {
  if(value >= 1){
    skillName.push(key);
  }
});
    workerList.workName = workName;
    workerList.skillName = skillName;
    workerList.searchId = this.searchId;

    this.selectedWork = new Map();
    this.selectedSkill = new Map();
    return workerList;
  }

  workEmpty(){
    if(this.works == undefined){
      return false;
    }
    return true;
  }

  chooseSkill(works : Array<WorkType>){
    this.works = works;
  }

  selectWork(workId : string){
    this.setClass(workId, "selected");
  }

  selectSkill(skillId : string){
    this.show = true;

    if(this.lastSkillName == undefined){
    this.setSkillClass(skillId, "selectSkill");
    this.lastSkillName = skillId;
  }
  else{
    this.setSkillClass(skillId, "selectSkill");
    this.setSkillClass(this.lastSkillName, "non-selectSkill");
    this.lastSkillName = skillId;
  }
}


}
