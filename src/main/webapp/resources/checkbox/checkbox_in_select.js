const selectHeader = document.querySelector('.select');

document.addEventListener('click', selectToggle);
document.addEventListener('keydown', selectToggleEnter);
window.addEventListener('resize', removeNameSelect);

//SELECT HEADER
function selectToggle(event) {
    if (event.target.closest('.select__header')) {
        selectHeader.classList.toggle('is-active');
        spoilerDefault();
    }

    if (!event.target.closest('.form')) {
        selectHeader.classList.remove('is-active');
        spoilerDefault();
    }
}

function selectToggleEnter(event) {
    if ((event.code === 'Enter') && event.target.closest('.select__header')) {
        selectHeader.classList.toggle('is-active');
        spoilerDefault();
    }

    if ((event.code === 'Enter') && !event.target.closest('.form')) {
        selectHeader.classList.remove('is-active');
        spoilerDefault();
    }
}


//SPOILER
const noSpan = document.querySelectorAll('.select__vertical');
let deleteSpan = false; // изначально span'ы не удалены

// Удаление заголовка каждого столбца, когда сразу на маленьких устройствах
if (window.innerWidth <= 700) {removeSpan();}

// Удаление заголовка каждого столбца, когда появляются спойлеры
function removeNameSelect() {
    if (window.innerWidth <= 700 && !deleteSpan) {
        removeSpan();
    }
    if (window.innerWidth > 700 && deleteSpan) {
        addSpan();
    }
}

function removeSpan() {
    const span = document.querySelectorAll('.select__vertical>span');

    for(item of span) {
        item.remove();
    }
    deleteSpan = true;
}

function addSpan() {
    let i = 1;
    for(item of noSpan) {
        item.insertAdjacentHTML('afterbegin', `<span>E${i}00-E${i}99</span>`);
        i++;
    }
    deleteSpan = false;
}

//Checkbox
const checkboxForm = document.querySelector('.form');
const checkboxes = document.querySelectorAll('.select__item');

// Проверка на checked чекбокса
checkboxForm.addEventListener('submit', function (event) {
    let massage = document.getElementById('massageCheckbox');
    let checkedCheckbox = false;
    event.preventDefault();

    for (item of checkboxes) {
        if (item.checked) {
            if (massage != null) {
                massage.remove();
            }
            checkedCheckbox = true;
            this.submit();
        }
    }

    for (item of checkboxes) {
        item.checked = false;
    }

    if (massage === null && !checkedCheckbox) {
        document.querySelector('.select').insertAdjacentHTML('beforeend', `<div class="massage" id="massageCheckbox">Пожалуйста, выберите хотя бы одну пищевую добавку!</div>`);
    }
});


// Добавление класса spoiler-is-active, когда спойлер открыт
const spoiler = document.querySelectorAll('.spoiler');

for(item of spoiler) {
    item.addEventListener('click', spoilerToggle);
}

function spoilerToggle() {
    this.classList.toggle('spoiler-is-active');
}

function spoilerDefault() {
    for(item of spoiler) {
        item.classList.remove('spoiler-is-active');
    }
}

// Проверка textarea на наличие символов
const feedbackForm = document.querySelector('.feedback__form');
const feedbackTextArea = document.querySelector('.feedback__textarea');

feedbackForm.addEventListener('submit', function(event) {
    let massage = document.getElementById('massageTextArea');
    // let massageSpace = document.getElementById('massageTextAreaSpace');
    let sentForm = false;
    event.preventDefault();

    if (feedbackTextArea.value.length > 0 && !(feedbackTextArea.value.charAt(0) === ' ')) {
        if (massage != null) {
            massage.remove();
        }
        sentForm = true;
        this.submit();
    }

    // if (feedbackTextArea.value.charAt(0) === ' ' && massageSpace === null) {
    //     feedbackTextArea.insertAdjacentHTML('afterend', `<div class="massage" id="massageTextAreaSpace">Пожалуйста, не пишите пробел в начале своего сообщения!</div>`);
    // }
    //
    // if (!(feedbackTextArea.value.charAt(0) === ' ') && massageSpace != null) {
    //     feedbackTextArea.insertAdjacentHTML('afterend', `<div class="massage" id="massageTextAreaSpace">Пожалуйста, не пишите пробел в начале своего сообщения!</div>`);
    // }

    if (massage === null && !sentForm) {
        feedbackTextArea.insertAdjacentHTML('afterend', `<div class="massage" id="massageTextArea">Пожалуйста, напишите хотя бы что-то!</div>`);
    }

    feedbackTextArea.value = '';
});