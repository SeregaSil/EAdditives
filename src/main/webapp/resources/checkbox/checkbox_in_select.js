const selectHeader = document.querySelector('.select');

document.addEventListener('click', selectToggle);
document.addEventListener('keydown', selectToggleEnter);
window.addEventListener('resize', removeNameSelect);

//SELECT HEADER
function selectToggle(event) {
    if (event.target.closest('.select__header')) {
        selectHeader.classList.toggle('is-active');
        spollerDefault();
    }

    if (!event.target.closest('.form')) {
        selectHeader.classList.remove('is-active');
        spollerDefault();
    }
}

function selectToggleEnter(event) {
    if ((event.code === 'Enter') && event.target.closest('.select__header')) {
        selectHeader.classList.toggle('is-active');
        spollerDefault();
    }

    if ((event.code === 'Enter') && !event.target.closest('.form')) {
        selectHeader.classList.remove('is-active');
        spollerDefault();
    }
}


//SPOLLER
const addSpan = document.querySelectorAll('.select__vertical');
let deleteSpan = false; // изначально span'ы не удалены

// Удаление заголовка каждого столбца, когда появляются спойлеры
function removeNameSelect() {
    let i = 1;

    if (window.innerWidth <= 700 && !deleteSpan) {
        const removeSpan = document.querySelectorAll('.select__vertical>span');
        for(item of removeSpan) {
            item.parentNode.removeChild(item);
        }
        deleteSpan = true;
    }

    if (window.innerWidth > 700 && deleteSpan) {
        for(item of addSpan) {
            item.insertAdjacentHTML('afterbegin', `<span>E${i}00-E${i}99</span>`);
            i++;
        }
        deleteSpan = false;
    }
}

// Добавление класса spoller-is-active, когда спойлер открыт
const spoller = document.querySelectorAll('.spoller');

console.log(spoller);

for(item of spoller) {
    item.addEventListener('click', spollerToggle);
}

function spollerToggle() {
    this.classList.toggle('spoller-is-active');
}

function spollerDefault() {
    for(item of spoller) {
        item.classList.remove('spoller-is-active');
    }
}