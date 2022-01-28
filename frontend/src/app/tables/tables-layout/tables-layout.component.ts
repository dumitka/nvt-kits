import { Component, OnInit } from '@angular/core';
import { TablesService } from '../tables.service';
import interact from 'interactjs';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-tables-layout',
  templateUrl: './tables-layout.component.html',
  styleUrls: ['./tables-layout.component.css']
})
export class TablesLayoutComponent implements OnInit {

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(public service: TablesService, private snackBar: MatSnackBar) { 

    interact('.dropzone').dropzone({
      accept: '#yes-drop',

      overlap: 1,

      ondropactivate: function (event) {
        // add active dropzone feedback
        event.target.classList.add('drop-active')
      },
      ondragenter: function (event) {
        var draggableElement = event.relatedTarget
        var dropzoneElement = event.target
    
        // feedback the possibility of a drop
        dropzoneElement.classList.add('drop-target')
        draggableElement.classList.add('can-drop')
        draggableElement.textContent = 'Dragged in'

      },
      ondragleave: function (event) {
        // remove the drop feedback style
        event.target.classList.remove('drop-target')
        event.relatedTarget.classList.remove('can-drop')
        event.relatedTarget.textContent = 'Dragged out'

        service.delete(event.relatedTarget.getAttribute("name"))
        .subscribe((data:any) => {
          console.log("OBRISALI SMO " + data)
          event.relatedTarget.classList.remove('purple-class')
          event.relatedTarget.classList.add('gray-class');
          
        })

      },
      ondrop: function (event) {
        event.relatedTarget.textContent = 'Dropped' //ovde onend?
        console.log("---------------------DROP---------------")

        console.log("UNUTRA DROP")
        
        //ovde uzeti coords?
        const restaurant = document.getElementById("restaurant");
        var rect = restaurant.getBoundingClientRect();
        console.log(`------Restoran x y : ${rect.x}  ${rect.y}`)

        var table_rect = event.relatedTarget.getBoundingClientRect();
        console.log(`------Sto x y : ${table_rect.x}  ${table_rect.y}`)

        console.log(`----------RELATIVNA POZICIJA KOJU CUVAMO x y :  ${table_rect.x - rect.x }  ${table_rect.y - rect.y}`)

        service.addNewOrUpdate({
          id: event.relatedTarget.getAttribute("name"),
          deskStatus: "NOT_ORDERED", //enum
          tip: 0,
          
          x: table_rect.x - rect.x,
          y: table_rect.y - rect.y,
          height: table_rect.height,
          width: table_rect.width,

          reserved: false,
        }).subscribe((data:any) => {
          console.log(data)
          event.relatedTarget.setAttribute("name", data.id);
          event.relatedTarget.classList.remove('gray-class');
          event.relatedTarget.classList.add('purple-class');

        })

        // const parent = document.getElementById("screen");

        // const newDiv = document.createElement("div");
        // newDiv.setAttribute("name", "0");
        // newDiv.setAttribute("id", "yes-drop");
        // // newDiv.className = 'resize-drag'
        // newDiv.classList.add('resize-drag');
        // newDiv.classList.add('can-drop');
        // newDiv.innerHTML = "Test";
        // console.log(newDiv)
        // newDiv.setAttribute('data-x', "50")
        // newDiv.setAttribute('data-y', "400")
        // parent.appendChild(newDiv);



        // document.body.appendChild(newDiv);
        console.log("OPEN SNAC BARR")
        // openSnackBar("Uspešno ste izmenili sto", this.RESPONSE_OK); //NE VALJA
        
        
      },
      ondropdeactivate: function (event) {
        // remove active dropzone feedback
        event.target.classList.remove('drop-active')
        event.target.classList.remove('drop-target')
      }
    })

    interact('.resize-drag')
    .resizable({
    edges: { left: true, right: true, bottom: true, top: true },

    listeners: {
      move (event) {
        var target = event.target
        var x = (parseFloat(target.getAttribute('data-x')) || 0)
        var y = (parseFloat(target.getAttribute('data-y')) || 0)

        // update the element's style
        target.style.width = event.rect.width + 'px'
        target.style.height = event.rect.height + 'px'

        // translate when resizing from top or left edges
        x += event.deltaRect.left
        y += event.deltaRect.top

        target.style.transform = 'translate(' + x + 'px,' + y + 'px)'

        target.setAttribute('data-x', x)
        target.setAttribute('data-y', y)
        target.textContent = Math.round(event.rect.width) + '\u00D7' + Math.round(event.rect.height)
      },

      end (event) {
        console.log("Ello! Ovo je reakcija na resize-drag")
        var element = event.target;
        var table_rect = element.getBoundingClientRect();
        service.resize(element.getAttribute("name"), {
          height: table_rect.height,
          width: table_rect.width
        }).subscribe((data: any) => {
            console.log("USPESNO RISAJZOVALI");
            console.log(data)
          }
        )

          // var textEl = event.target.querySelector('p')
        
        // let parent = event.target.parentElement;

        //////gde ovo?
        // const parent = document.getElementById("restaurant");
        // let xPixel = event.target.getAttribute('data-x');
 

        // let elementId = event.target.id;

        /////
      }
    },
    modifiers: [
      // keep the edges inside the parent
      interact.modifiers.restrictEdges({
        outer: 'parent'
      }),

      // minimum size
      interact.modifiers.restrictSize({
        min: { width: 100, height: 50 }
      })
    ],

    })
    .draggable({
      listeners: { 
        move: dragMoveListener,
        end : function(event) {
          //SAMO AKO JE U RESTORANU
          console.log("Ello! ovo je reakcija na DRAGGABLE  hm treba ove piksele uhvatiti")
        }
      },
      modifiers: [
        interact.modifiers.restrictRect({
          restriction: 'parent',
          endOnly: true
        })
      ]
    })

    function dragMoveListener (event) {
      var target = event.target
      // keep the dragged position in the data-x/data-y attributes
      var x = (parseFloat(target.getAttribute('data-x')) || 0) + event.dx
      var y = (parseFloat(target.getAttribute('data-y')) || 0) + event.dy

      // translate the element
      target.style.transform = 'translate(' + x + 'px, ' + y + 'px)'

      // update the posiion attributes
      target.setAttribute('data-x', x)
      target.setAttribute('data-y', y)
    }
  }

  ngOnInit(): void {
  }


  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}
