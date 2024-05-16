/**
 *
 */

$(document).ready(function () {
    $('.update-button').on('click', function (event) {
        event.preventDefault();

        let adviserId = $(this).attr('id');

        $.get(`/adviser/update/${adviserId}`, function (adviser) {
            $('#IdEdit').val(adviser.id);
            $('#nameEdit').val(adviser.name);
            $('#departmentEdit').val(adviser.department);

            $('#updateAdviserModalCenter').modal('show');
        }).fail(function () {
            console.error("Error loading adviser data");
        });
    });
});

$(document).ready(function () {
    $('.delete-button').on('click', function (event) {
        event.preventDefault();

        let adviserId = $(this).attr('id');
        $.get(`/adviser/delete/${adviserId}`, function (adviser) {
            $('#deleteIdEdit').val(adviser.id);
            $('#deleteNameEdit').val(adviser.name);
            $('#deleteDepartmentEdit').val(adviser.department);

            $('#deleteAdviserModalCenter').modal('show');
        }).fail(function () {
            console.error("Error loading adviser data");
        });

    });
});



$(document).on("click", ".supervise-button", function () {
    let adviserId = $(this).attr('id');
    $("#adviserId").val(adviserId);

    let adviserName = $(this).closest('tr').find('td:nth-child(2)').text();
    $("#adviserName").val(adviserName);

    $.ajax({
        type: "GET",
        url: "/study/getStudies",
        success: function(data) {
            let options = "";
            $.each(data, function(key, study) {
                options += "<option value='" + study.id + "'>" + study.title + "</option>";
            });
            $("#studySelect").html(options);
        }
    });
});

$(document).ready(function () {
    $('#modals-container').load('modals/adviser/modals-adviser.html');
});




