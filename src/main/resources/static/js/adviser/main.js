/**
 *
 */

$(document).ready(function () {
    $('.update-button').on('click', function (event) {
        event.preventDefault();

        let adviserId = $(this).attr('id');

        $.ajax({
            url: '/api/auth/isAdmin',
            type: 'GET',
            success: function (isAdmin) {
                if (isAdmin) {
                    $.get(`/adviser/update/${adviserId}`, function (adviser) {
                        $('#IdEdit').val(adviser.id);
                        $('#nameEdit').val(adviser.name);
                        $('#departmentEdit').val(adviser.department);

                        $('#updateAdviserModalCenter').modal('show');
                    }).fail(function () {
                        console.error("Error loading adviser data");
                    });
                } else {
                    alert('Unauthorized action');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('Error during isAdmin check:', textStatus, errorThrown);
                alert('An error occurred during isAdmin check: ' + textStatus);
            }
        });
    });
});



$(document).ready(function () {
    $('.delete-button').on('click', function (event) {
        event.preventDefault();

        let adviserId = $(this).attr('id');

        $.ajax({
            url: '/api/auth/isAdmin',
            type: 'GET',
            success: function (isAdmin) {
                if (isAdmin) {
                    $.get(`/adviser/delete/${adviserId}`, function (adviser) {
                        $('#deleteIdEdit').val(adviser.id);
                        $('#deleteNameEdit').val(adviser.name);
                        $('#deleteDepartmentEdit').val(adviser.department);

                        $('#deleteAdviserModalCenter').modal('show');
                    }).fail(function () {
                        console.error("Error loading adviser data");
                        alert('Error loading adviser data');
                    });
                } else {
                    alert('Unauthorized operation');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('Error during isAdmin check:', textStatus, errorThrown);
                alert('Error during isAdmin check: ' + textStatus);
            }
        });
    });
});




$(document).on("click", ".supervise-button", function (event) {
    event.preventDefault();

    let adviserId = $(this).attr('id');
    $("#adviserId").val(adviserId);

    let adviserName = $(this).closest('tr').find('td:nth-child(2)').text();
    $("#adviserName").val(adviserName);

    $.ajax({
        url: '/api/auth/isAdmin',
        type: 'GET',
        success: function (isAdmin) {
            if (isAdmin) {
                $.ajax({
                    type: "GET",
                    url: "/study/getStudies",
                    success: function (data) {
                        let options = "";
                        $.each(data, function (key, study) {
                            options += "<option value='" + study.id + "'>" + study.title + "</option>";
                        });
                        $("#studySelect").html(options);
                    },
                    error: function () {
                        console.error("Error loading study data");
                        alert('Error loading study data');
                    }
                });
            } else {
                alert('Unauthorized operation');
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('Error during isAdmin check:', textStatus, errorThrown);
            alert('Error during isAdmin check: ' + textStatus);
        }
    });
});


$(document).ready(function () {
    $.ajax({
        url: '/api/auth/isAdmin',
        type: 'GET',
        success: function (isAdmin) {
            if (isAdmin) {
                $('#modals-container').load('modals/adviser/modals-adviser.html');
            } else {
               // alert('Unauthorized operation');
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('Error during isAdmin check:', textStatus, errorThrown);
            alert('Error during isAdmin check: ' + textStatus);
        }
    });
});






