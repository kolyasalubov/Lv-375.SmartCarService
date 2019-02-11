import { Component, OnInit } from '@angular/core';
import { Skill } from './Skill';
import { SkillService } from './skill-service'

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss'],
  providers: [SkillService]
})
export class StoSkillComponent implements OnInit {

  showWorkerList :boolean = false;

  myskill : Map<string, number> = new Map();
  timeValue : number = 0;
  skills : Array<Skill>;
  error: ErrorEvent;
  searchId : number = 1;

  constructor(private skillService: SkillService) {}

  ngOnInit() {
     this.skillService.getAllSkillsToStoResp(this.searchId)
    .subscribe((data : Skill[])=> this.skills = data,
    error => this.error = error);
  }



  addOrDelete(s : Skill){
    if(this.myskill.get(s.name) != undefined){
      if(this.myskill.get(s.name) == -1){
        this.timeValue += s.requiredTime;
        this.myskill.set(s.name, 1);
        this.setClass(s.id, 'selected');
      }
      else{
        this.timeValue -= s.requiredTime;
        this.myskill.set(s.name, -1);
        this.setClass(s.id, 'non-selected');
      }
  }
  else{
    this.myskill.set(s.name, 1);
    this.timeValue += s.requiredTime;
    this.setClass(s.id, 'selected');
  }
    
  }

  private setClass(id: number, className: string): void{
    document.getElementById(String(id)).className = "list-group-item d-flex justify-content-between " + className;
  }

  changeShow(){
    this.showWorkerList = true;
    console.log("TUTURU");
  }
}
