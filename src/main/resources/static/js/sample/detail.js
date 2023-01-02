const list = (() => {
    const frm = document.querySelector("#frm");
    const btnSave = document.querySelector("#btn_save");

    return {
        init: function() {
            btnSave.addEventListener("click", this.save);
        },
        save: function() {
            frm.submit();
        }
    }
})();

list.init();


