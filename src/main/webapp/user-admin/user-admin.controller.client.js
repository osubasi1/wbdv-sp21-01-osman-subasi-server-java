var $usernameFld, $passwordFld;
var $firstNameFld, $lastNameFld, $roleFld;
var $removeBtn, $editBtn, $createBtn;
var $searchBtn, $updateBtn;
var $userRowTemplate, $tbody;
var userService = new AdminUserServiceClient();
var users = []

function createUser() {
    var newUser = {
        username: $usernameFld.val(),
        password: $passwordFld.val(),
        firstname: $firstNameFld.val(),
        lastname: $lastNameFld.val(),
        role: $roleFld.val()
    }
    userService.createUser(newUser)
        .then(function (actualUser) {
            users.push(actualUser)
            // alert("it is clickable")
            renderUsers(users)
        })
    emptyFields()
}

function deleteUser(event) {
    var button = $(event.target)
    var index = button.attr("id")
    var id = users[index]._id
    userService.deleteUser(id)
        .then(function () {
            users.splice(index, 1)
            renderUsers(users)
        })
}

var selectedUser = null

function selectUser(event) {
    var id = $(event.target).attr("id")
    selectedUser = users.find(user => user._id === id)
    $usernameFld.val(selectedUser.username)
    $passwordFld.val(selectedUser.password)
    $firstNameFld.val(selectedUser.firstname)
    $lastNameFld.val(selectedUser.lastname)
    $roleFld.val(selectedUser.role)

}

function emptyFields() {

    $usernameFld.val("");
    $passwordFld.val("");
    $firstNameFld.val("");
    $lastNameFld.val("");
    $roleFld.val("");

}

function updateUser() {
    selectedUser.username = $usernameFld.val()
    selectedUser.password = $passwordFld.val()
    selectedUser.firstname = $firstNameFld.val()
    selectedUser.lastname = $lastNameFld.val()
    selectedUser.role = $roleFld.val()
    userService.updateUser(selectedUser._id, selectedUser)
        .then(function () {
            var index = users.findIndex(user => user._id === selectedUser._id)
            users[index] = selectedUser
            renderUsers(users)
        })
    emptyFields()
}

function renderUsers(users) {
    $tbody.empty()
    for (var i = 0; i < users.length; i++) {
        var user = users[i]
        $tbody
            .prepend(`
                        <tr class="wbdv-template wbdv-user wbdv-hidden">
            <td class="wbdv-username">${user.username}</td>
            <td>&nbsp;</td>
            <td class="wbdv-first-name">${user.firstname}</td>
            <td class="wbdv-last-name">${user.lastname}</td>
            <td class="wbdv-role">${user.role}</td>
            <td class="wbdv-actions">
                <span class="pull-right">
                    <i class="fa-2x fa fa-times wbdv-remove" id="${i}"></i>
                    <i class="fa-2x fa fa-pencil wbdv-edit" id="${user._id}"></i>
                </span>
            </td>
        </tr>
                `)
    }
    $(".wbdv-remove").click(deleteUser)
    $(".wbdv-edit").click(selectUser)
}

// function findAllUsers() { } // optional - might not need this
// function findUserById() {  } // optional - might not need this
function main() {

    $tbody = jQuery("tbody#wbdv-tbody-1")
    $removeBtn = $(".wbdv-remove")
    $editBtn = $(".wbdv-edit")
    $createBtn = $(".wbdv-create")
    $updateBtn = $(".wbdv-update")
    $searchBtn = $(".wbdv-search")

    $usernameFld = $("#usernameFld")
    $passwordFld = $("#passwordFld")
    $firstNameFld = $("#firstNameFld")
    $lastNameFld = $("#lastNameFld")
    $roleFld = $("#roleFld")

    $editBtn.click(selectUser)
    $updateBtn.click(updateUser)
    $createBtn.click(createUser)
    $removeBtn.click(deleteUser)

    userService.findAllUsers().then(function (actualUsers) {
        users = actualUsers

        renderUsers(users)
    })

}

$(main);



