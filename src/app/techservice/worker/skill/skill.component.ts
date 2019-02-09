import { Component, OnInit } from '@angular/core';
import { Skill } from './skill';
import { SkillService } from './skill.service';
import { THROW_IF_NOT_FOUND } from '@angular/core/src/di/injector';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.scss']
})
export class SkillComponent implements OnInit {

  skills: Skill[];
  error: ErrorEvent;

  constructor(private skillService: SkillService) { }

  ngOnInit() {
    console.log(this.skillService.getAllSkills()
    .subscribe(data => this.skills = data,
              error => this.error = error));
  }
}
