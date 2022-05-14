const select = function () {
    let selectHeader = document.querySelector('.select');

    document.addEventListener('click', selectToggle);
    document.addEventListener('keydown', selectToggleEnter);

    function selectToggle(event) {
        if (event.target.closest('.select__header')) {
            selectHeader.classList.toggle('is-active');
        }

        if (!event.target.closest('.form')) {
            selectHeader.classList.remove('is-active');
        }
    }
    function selectToggleEnter(event) {
        if ((event.code === 'Enter') && event.target.closest('.select__header')) {
            selectHeader.classList.toggle('is-active');
        }

        if ((event.code === 'Enter') && !event.target.closest('.select')) {
            selectHeader.classList.remove('is-active');
        }
    }
};

select();
