// DOMContentLoaded: Fires with the document object model is fully loaded
document.addEventListener("DOMContentLoaded", function() {
	// Create a GET request to retrieve ALL movies and add them to the table 

	// 1. Make an XHR object
	let xhr = new XMLHttpRequest(); // Makes HTTP requests

	// 2. Define what happens during the AJAX call
	xhr.onreadystatechange = function() {
		// Ready States
		// 0 - Unsent
		// 1 - Opened
		// 2 - Headers recieved
		// 3 - Loading
		// 4 - Done

		if (xhr.readyState === 4) {
			// ONLY do something once the request is completed
			console.log(JSON.parse(xhr.responseText)); // JSON.parse() to parse JSON

			var animalArray = JSON.parse(xhr.responseText); // Creating an array of JSON movie objects
			
			animalArray.sort( function (a, b) {
				return a.id - b.id;
			});
			
			//Looping through the array and adding each element to the table
			animalArray.forEach(animal => {
				addAnimalToTable(animal);
			});
			
		}
	}

	// 3. Open the XHR call with the http request verb and the URL
	xhr.open('GET', '/enterprise-project-one/api/zoo');

	// 4. Send the AJAX call
	xhr.send();

});

//Add animal to the table on html
function addAnimalToTable (animal) {
	
	// Creating all of our needed DOM elements
	var tr = document.createElement('tr'); 
	var id = document.createElement('td');
	var name = document.createElement('td');
	var scientific_name = document.createElement('td');
	var life_span = document.createElement('td');
	var weight_lb = document.createElement('td');
	var diet = document.createElement('td');
	var endangered_status = document.createElement('td');
	var picture = document.createElement('td');
	
	//Add actions section
	var actions = document.createElement('td');
	actions.style.whiteSpace = 'nowrap';
	
	//adding data to the elements
	id.innerText = animal.id;
	name.innerText = animal.name;
	scientific_name.innerText = animal.scientific;
	life_span.innerText = animal.lifespan;
	weight_lb.innerText = animal.weight;
	diet.innerText = animal.diet;
	endangered_status.innerText = animal.status;
	picture.innerText = animal.picurl;
	
	//Add buttons to action
	actions.innerHTML = "<button id='edit' type='button' onclick='editAnimal(this)' class='btn btn-primary'><i class='fas fa-edit'></i></button>" +
		"<button id='delete' type='button' onclick='deleteAnimal(this);' class='btn btn-primary'><i class='fas fa-times'></i></button>";
	
	// Add <td>'s to our <tr>
	tr.appendChild(id);
	tr.appendChild(name);
	tr.appendChild(scientific_name);
	tr.appendChild(life_span);
	tr.appendChild(weight_lb);
	tr.appendChild(diet);
	tr.appendChild(endangered_status);
	tr.appendChild(picture);
	
	// Add edit/delete button to actions
	tr.appendChild(actions)
	
	// Add our <tr> to our <tbody>
	document.getElementById("zoo-table-body").appendChild(tr);
	
}

//Delete an animal from the table
function deleteAnimal (btn) {
	
	// 1. Make an XHR object
	let xhr = new XMLHttpRequest(); // Makes HTTP requests

	var cells = btn.closest("tr").cells;
	//Create an animal to send to the database to delete
	var animal = {
		id : cells[0].innerText,
		name : cells[1].innerText,
		scientific : cells[2].innerText,
		lifespan : cells[3].innerText,
		weight : cells[4].innerText,
		diet : cells[5].innerText,
		status : cells[6].innerText,
		picurl : cells[7].innerText
	};

	// 3. Open the XHR call with the http request verb and the URL
	xhr.open('DELETE', '/enterprise-project-one/api/zoo');

	// 4. Send the AJAX call
	xhr.send(JSON.stringify(animal));
	
	//Delete row
	btn.closest("tr").remove();
}

//Edit animal button
function editAnimal (btn) {
	
	var cells = btn.closest("tr").cells;
	
	//console.log(cells[0].innerHTML);
	
	//Change button to save
	btn.outerHTML = "<button type='button' onclick='updateAnimal(this)' class='btn btn-primary'><i class='far fa-save'></i></button>";
	//Save old animal results
	var oldAnimal = {
		id : cells[0].innerText,
		name : cells[1].innerText,
		scientific : cells[2].innerText,
		lifespan : cells[3].innerText,
		weight : cells[4].innerText,
		diet : cells[5].innerText,
		status : cells[6].innerText,
		picurl : cells[7].innerText
	};
	
	//place old animal into input boxes
	cells[0].innerHTML = "<input type='text' id='editInput' value='" + oldAnimal.id + "'>";
	cells[1].innerHTML = "<input type='text' id='editInput' value='" + oldAnimal.name + "'>";
	cells[2].innerHTML = "<input type='text' id='editInput' value='" + oldAnimal.scientific + "'>";
	cells[3].innerHTML = "<input type='text' id='editInput' value='" + oldAnimal.lifespan + "'>";
	cells[4].innerHTML = "<input type='text' id='editInput' value='" + oldAnimal.weight + "'>";
	cells[5].innerHTML = "<input type='text' id='editInput' value='" + oldAnimal.diet + "'>";
	cells[6].innerHTML = "<input type='text' id='editInput' value='" + oldAnimal.status + "'>";
	cells[7].innerHTML = "<input type='text' id='editInput' value='" + oldAnimal.picurl + "'>";
	
}

//Update animal button
function updateAnimal(btn) {
	
	var cells = btn.closest("tr").cells;
	
	//Change button to edit
	btn.outerHTML = "<button type='button' onclick='editAnimal(this)' class='btn btn-primary'><i class='fas fa-edit'></i></button>";
	
	//Save new animal results
	var newAnimal = {
		id : cells[0].childNodes[0].value,
		name : cells[1].childNodes[0].value,
		scientific : cells[2].childNodes[0].value,
		lifespan : cells[3].childNodes[0].value,
		weight : cells[4].childNodes[0].value,
		diet : cells[5].childNodes[0].value,
		status : cells[6].childNodes[0].value,
		picurl : cells[7].childNodes[0].value
	};
	
	//place new animal into table row
	cells[0].innerText = newAnimal.id;
	cells[1].innerText = newAnimal.name;
	cells[2].innerText = newAnimal.scientific;
	cells[3].innerText = newAnimal.lifespan;
	cells[4].innerText = newAnimal.weight;
	cells[5].innerText = newAnimal.diet;
	cells[6].innerText = newAnimal.status;
	cells[7].innerText = newAnimal.picurl;
	
	// 1. Make an XHR object
	let xhr = new XMLHttpRequest(); // Makes HTTP requests
	
	// 3. Open the XHR call with the http request verb and the URL
	xhr.open('PUT', '/enterprise-project-one/api/zoo');

	// 4. Send the AJAX call
	xhr.send(JSON.stringify(newAnimal));
	
}













