/**
 *
 */
$(document).ready(function() {
    $('.update-button').on('click', function(event) {
        event.preventDefault();

        let studyId = $(this).attr('id');
        $.ajax({
            url: '/api/auth/isAdmin',
            type: 'GET',
            success: function(isAdmin) {
                if (isAdmin) {
                    $.get(`/study/update/${studyId}`, function(study) {
                        $('#IdEdit').val(study.id);
                        $('#titleEdit').val(study.title);
                        $('#descriptionEdit').val(study.description);

                        $('#updateStudyModalCenter').modal('show');
                    }).fail(function() {
                        console.error("Error loading study data");
                        alert('Error loading study data');
                    });
                } else {
                    alert('Unauthorized operation');
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error during isAdmin check:', textStatus, errorThrown);
                alert('Error during isAdmin check: ' + textStatus);
            }
        });
    });
});


$(document).ready(function() {
    $('.delete-button').on('click', function(event) {
        event.preventDefault();

        let studyId = $(this).attr('id');

        $.ajax({
            url: '/api/auth/isAdmin',
            type: 'GET',
            success: function(isAdmin) {
                if (isAdmin) {
                    $.get(`/study/delete/${studyId}`, function(study) {
                        $('#deleteId').val(study.id);
                        $('#deleteTitle').val(study.title);
                        $('#deleteDescription').val(study.description);

                        $('#deleteStudyModalCenter').modal('show');
                    }).fail(function() {
                        console.error("Error loading study data");
                        alert('Error loading study data');
                    });
                } else {
                    alert('Unauthorized operation');
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error during isAdmin check:', textStatus, errorThrown);
                alert('Error during isAdmin check: ' + textStatus);
            }
        });
    });
});


$(document).on("click", ".supervise-button", function (event) {
    event.preventDefault();

    let studyId = $(this).attr('id');
    $("#studyId").val(studyId);

    let studyName = $(this).closest('tr').find('td:nth-child(2)').text();
    $("#studyName").val(studyName);

    $.ajax({
        url: '/api/auth/isAdmin',
        type: 'GET',
        success: function(isAdmin) {
            if (isAdmin) {
                $.ajax({
                    type: "GET",
                    url: "/adviser/getAdvisers",
                    success: function(data) {
                        let options = "";
                        $.each(data, function(key, adviser) {
                            options += "<option value='" + adviser.id + "'>" + adviser.name + "</option>";
                        });
                        $("#adviserSelect").html(options);
                    },
                    error: function() {
                        console.error("Error loading adviser data");
                        alert('Error loading adviser data');
                    }
                });
            } else {
                alert('Unauthorized operation');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
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
                $('#modals-container').load('modals/study/modals-study.html');
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

$(document).ready(function () {
    $('.add-button').on('click', function () {
        $.ajax({
            url: '/api/auth/isAdmin',
            type: 'GET',
            success: function (isAdmin) {
                if (isAdmin) {
                    $('#addStudyModalCenter').modal('show');
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




