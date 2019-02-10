import { Component, OnInit } from '@angular/core';
import { Skill } from './Skill';
import { from } from 'rxjs';
import { SkillService } from './skill-service'

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss'],
  providers: [SkillService]
})
export class StoSkillComponent implements OnInit {

  myskill : Map<string, number> = new Map();
  timeValue : number = 0;
  skills : Array<Skill>;
  error: ErrorEvent;

  constructor(private skillService: SkillService) {}

  ngOnInit() {
     this.skillService.getAllSkillsToStoResp()
    .subscribe((data : Skill[])=> this.skills = data,
    error => this.error = error);
  }



  addOrDelete(s : Skill){
    if(this.myskill.get(s.name) != undefined){
      if(this.myskill.get(s.name) == -1){
        this.timeValue += s.requiredTime;
        this.myskill.set(s.name, 1);
      }
      else{
        this.timeValue -= s.requiredTime;
        this.myskill.set(s.name, -1);
      }
  }
  else{
    this.myskill.set(s.name, 1);
    this.timeValue += s.requiredTime;
  }
}

}
