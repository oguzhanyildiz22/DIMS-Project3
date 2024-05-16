/**
 *
 */
$(document).ready(function() {
    $('.update-button').on('click', function(event) {
        event.preventDefault();

        let studyId = $(this).attr('id');

        $.get(`/study/update/${studyId}`, function(study) {
            $('#IdEdit').val(study.id);
            $('#titleEdit').val(study.title);
            $('#descriptionEdit').val(study.description);

            $('#updateStudyModalCenter').modal('show');
        }).fail(function() {
            console.error("Error loading study data");
        });
    });
});

$(document).ready(function() {
    $('.delete-button').on('click', function(event) {
        event.preventDefault();

        let studyId = $(this).attr('id');
        $.get(`/study/delete/${studyId}`, function(study) {
            $('#deleteId').val(study.id);
            $('#deleteTitle').val(study.title);
            $('#deleteDescription').val(study.description);

            $('#deleteStudyModalCenter').modal('show');
        }).fail(function() {
            console.error("Error loading study data");
        });

    });
});

$(document).on("click", ".supervise-button", function () {
    let studyId = $(this).attr('id');
    $("#studyId").val(studyId);

    let studyName = $(this).closest('tr').find('td:nth-child(2)').text();
    $("#studyName").val(studyName);

    $.ajax({
        type: "GET",
        url: "/adviser/getAdvisers",
        success: function(data) {
            let options = "";
            $.each(data, function(key, adviser) {
                options += "<option value='" + adviser.id + "'>" + adviser.name + "</option>";
            });
            $("#adviserSelect").html(options);
        }
    });
});

$(document).ready(function() {
    $('#modals-container').load('modals/study/modals-study.html');
});

