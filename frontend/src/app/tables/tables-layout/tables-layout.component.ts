import { Component, OnInit } from '@angular/core';
import interact from 'interactjs';

@Component({
  selector: 'app-tables-layout',
  templateUrl: './tables-layout.component.html',
  styleUrls: ['./tables-layout.component.css']
})
export class TablesLayoutComponent implements OnInit {

  constructor() { 
    // target elements with the "draggable" class
    interact('.resize-drag')
    .resizable({
    // resize from all edges and corners
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
      listeners: { move: dragMoveListener },
      modifiers: [
        interact.modifiers.restrictRect({
          restriction: 'parent',
          endOnly: true
        })
      ]
    })

    // .draggable({
    //   // enable inertial throwing
    //   inertia: false,
    //   // keep the element within the area of it's parent
    //   modifiers: [
    //     interact.modifiers.restrictRect({
    //       restriction: 'parent',
    //       endOnly: false
    //     })
    //   ],
    //   // enable autoScroll
    //   autoScroll: false,

    //   listeners: {
    //     // call this function on every dragmove event
    //     move: dragMoveListener,

    //     // call this function on every dragend event
    //     end (event) {
    //       // var textEl = event.target.querySelector('p')

    //       // textEl && (textEl.textContent =
    //       //   'moved a distance of ' +
    //       //   (Math.sqrt(Math.pow(event.pageX - event.x0, 2) +
    //       //             Math.pow(event.pageY - event.y0, 2) | 0))
    //       //     .toFixed(2) + 'px')
    //       let parent = event.target.parentElement;
    //       let width = parent.offsetWidth;
    //       let height = parent.offsetHeight;

    //       let xPixel = event.target.getAttribute('data-x');
    //       let yPixel = event.target.getAttribute('data-y');
    //       let xNormalized = xPixel / width;
    //       let yNormalized = yPixel / height;

    //       let elementId = event.target.id;
    //       console.log(`Element with ID ${elementId} has moved to pixel coordinates x=${xPixel}, y=${yPixel}`);
    //       console.log(`Normalized x=${xNormalized}, y=${yNormalized}`);
    //       console.log(`... which is, converted back into pixel coordinates: x=${xNormalized * width}, y=${yNormalized * height}`);
    //     }
    //   }
    // })

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

}
