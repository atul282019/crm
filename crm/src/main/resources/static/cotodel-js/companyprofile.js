function autofillcompanyDetails() {
			    
	const urlParams = new URLSearchParams(window.location.search);
	const employerId = urlParams.get('employerId');
	document.getElementById('employerId').value=employerId;
			    $.ajax({
			        type: "POST",
			        url: "/getEmployerDetails",
			        data: { "employerId": employerId },
			        success: function(response) {
			            var data1 = jQuery.parseJSON(response);
						console.log("data1",data1.data);

			            if (data1.status && data1.data) {
			                var data = data1.data;

							// Populate fields
							//$("#fullName").text(data.name);
							$("#organizationName").text(data.organizationName);

							$("#adminname").text(data.name); //designation
							$("#email").text(data.email); // mngr name
							$("#mobno").text(data.mobno); // No marital status in response
							//$("#creationDate").text(data.creationDate); // No handicapped status in response
							//$("#creationDate").text(data.creationDate ? formatDisplayDate(data.creationDate) : "N/A");
							$("#location").text(data.location);
							
							
							$("#gstn").text(data.gstIdentificationNumber);
							$("#lglnameofBusiness").text(data.legalNameOfBusiness);
							$("#orgType").text(data.orgType);
							$("#address1").text(data.address1);
							$("#address2").text(data.address2);
							$("#districtName").text(data.districtName);
							$("#pincode").text(data.pincode);
							$("#stateName").text(data.stateName);
							
							$("#createdDate").text(data.createdDate);
							$("#companyType").text(data.companyType);
							
			            } else {
			                console.log("No data found for the given Employee ID.");
			            }
			        },
			        error: function(error) {
			            console.log("Error fetching data: " + error.responseText);
			        }
			    });
			}
			
			async function getVoucherTransactionList() {
				const orgId = document.getElementById('employerId').value;
			    $.ajax({
			        type: "POST",
			        url: "/getVoucherTransactionList",
					data: { "orgId":orgId,
							 "timePeriod":"AH"	
					 },
			        beforeSend: function(xhr) {
			            //xhr.setRequestHeader(header, token);
			        },
			        success: function(data) {
			            newData = data;
			            console.log("Emp onboarding data", newData);
			            var data1 = jQuery.parseJSON(newData);
			            var data2 = data1.data;
						console.log(" upi voucher issue getVoucherTransactionList()=",data1);
			            
			            var table = $('#vouchersTableTransactionList').DataTable({
			                destroy: true,
			                "responsive": true,
			                searching: false,
			                bInfo: false,
			                paging: false,
			                "lengthChange": true,
			                "autoWidth": false,
			                "pagingType": "full_numbers",
			                "pageLength": 50,
							
			                "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
			                "language": {
								"emptyTable": 'As per the last update, currently there are no UPI Vouchers transactions recorded on the platform. If your team members have redeemed</br> a UPI Voucher already, please refresh and check again at the end of the day to view corresponding transactions.</br>If your team and you haven’t already, start issuing and using <a href="/upiVoucherIssuanceNew">UPI Vouchers</a> to experience the magic!'
								},
							order: [[0, 'desc']],  // <--- force descending sort by creationDate
			                "aaData": data2,
			                "aoColumns": [
			                  //  { "mData": "creationDate" },
							  {
							    "mData": null,
							    "orderable": false,
							    "className": "dt-body-center",
							    "render": function (data, type, row, meta) {
							      return `<input type="checkbox" class="rowCheckbox" value="${row.merchanttxnId}" style="margin-left: 12px;">`;
							    }
							  },
								{ 
								  "mData": "creationDate", 
								  "render": function (data) {
								      return formatDate(data);
								  }
								},
								{ "mData": "merchanttxnId" },
								{ "mData": "bankrrn" },
								{
								  "mData": null,
								  "render": function (data, type, row) {
								    return `<div>${row.name}</div><div>${row.mobile}</div>`;
								  }
								},
								{
								  "mData": "purposeDesc",
								  "render": function (data, type, row) {
								   
								      return '<img src="data:image/png;base64,' + row.mccMainIcon + '" alt="" width="24px" height="24px" style="margin-top:-10px;">'+ data;
								    
								  }
								},
								{ "mData": "payeeName" },
								{
								  "mData": "redeemAmount",
								"class":"text-right",
								"render": function (data2, type, row) {
								    if (!data2) return '';
								    let amount = parseFloat(data2);
								    let formattedAmount = amount.toFixed(2); // enforce 2 decimal places
								    let localizedAmount = parseFloat(formattedAmount).toLocaleString('en-IN', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
								    return '<div class="amount-cell">₹' + localizedAmount + '</div>';
								}
								},
								
								//{ "mData": "merchanttxnId" },
								
			                ],

							
					});		
										
			        },
			        error: function(e) {
			            alert('Failed to fetch JSON data' + e);
			        }
			    });
			}
			async function getSupportTicketActionList() {
			  var orgId = document.getElementById("employerId").value;
			  //document.getElementById("signinLoader").style.display="flex";
			  $.ajax({
			    type: "GET",
			    url: "/getTicketListForAction",
			    data: {
			      orgId: orgId
			    },
			    beforeSend: function (xhr) {},
			    success: function (data) {
				  //document.getElementById("signinLoader").style.display="none";
			      const data1 = jQuery.parseJSON(data);
			   		var data2 = data1.data;
			      $('#TicketSupportActionTable').DataTable({
			        destroy: true,
			        responsive: true,
			        searching: false,
			        bInfo: false,
			        paging: false,
			        lengthChange: true,
			        autoWidth: false,
			        pagingType: "full_numbers",
			        pageLength: 50,
			        buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
			        language: { emptyTable: "No Tickets Found" },
			        aaData: data2,
					aoColumns: [
					 
					  { mData: "ticketNo" },
					 
					 
					  { mData: "subject" },
					  { mData: "creationDate"},
					  {
					    mData: "status",
					    render: function (data) {
					      return data === 0 ? "Submitted" : "Closed";
					    }
					  },
					 
					 
					]
			      });
			    },
			    error: function (e) {
			      alert('Failed to fetch JSON data' + e);
			    }
			  });
			}
			
			async function getEmployeeOnboarding() {
				//document.getElementById("signinLoader").style.display="flex";
			   // var employeeId = document.getElementById("employeeId").value;
			   var employeeId="";
			    var employerId = document.getElementById("employerId").value;
			    
				const clientKey = "client-secret-key"; // Extra security measure
			    const secretKey = "0123456789012345"; // SAME KEY AS BACKEND

			    // Concatenate data (must match backend)
			    const dataString = employerId+employeeId+clientKey+secretKey;

			    // Generate SHA-256 hash
			    const encoder = new TextEncoder();
			    const data = encoder.encode(dataString);
			    const hashBuffer = await crypto.subtle.digest("SHA-256", data);
			    const hashArray = Array.from(new Uint8Array(hashBuffer));
			    const hashHex = hashArray.map(byte => byte.toString(16).padStart(2, '0')).join('');
				const requestData = {
						employerId:employerId,
						employeeId:employeeId,
				        key: clientKey,  // Extra key for validation
				        hash: hashHex
				    };
			    $.ajax({
			        type: "POST",
			        url: "/getEmployeeOnboarding",
					contentType: "application/json",
					data: JSON.stringify(requestData),
			        beforeSend: function(xhr) {
			            //xhr.setRequestHeader(header, token);
			        },
			        success: function(data) {
						//document.getElementById("signinLoader").style.display="none";
			            newData = data;
			            console.log("Emp onboarding data", newData);
			            var data1 = jQuery.parseJSON(newData);
			            var data2 = data1.data;
			            
			            // Filter employees with status 1 (Active)
			            var filteredData = data2.filter(function(employee) {
			                return employee.status === 1;
			            });
						// Save Name and Mobile only for temporary session use
			            const nameMobileOnly = filteredData.map(emp => ({
			                name: emp.name,
			                mobile: emp.mobile
			            }));
			            sessionStorage.setItem("nameMobileOnly", JSON.stringify(nameMobileOnly));
			            
			            var table = $('#employeeTable').DataTable({
			                destroy: true,
			                "responsive": true,
			                searching: false,
			                bInfo: false,
			                paging: false,
			                "lengthChange": true,
			                "autoWidth": false,
			                "pagingType": "full_numbers",
			                "pageLength": 50,
			                "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
			                "language": {"emptyTable": "No Active Employees Found"},
			                
			                // Use the filtered data instead of original data
			                "aaData": filteredData,
			                "aoColumns": [
			                    { "mData": null, "render": function(data, type, row, meta) { return meta.row + 1; } },
			                    { "mData": "id", "render": function(data1, type, row) {
			                        return '<input type="hidden" class="form-input" id="id" name="id" value="' + data1 + '">';
			                    }},
			                    { "mData": "userDetailsId", "render": function(data1, type, row) {
			                        return '<input type="hidden" class="form-input" id="userDetailsId" name="userDetailsId" value="' + data1 + '">';
			                    }},
			                    { "mData": "name" },
			                    { "mData": "mobile", "className": "text-left"},
			                    { "mData": "email" , "className": "text-left"},
			                    { "mData": "empOrCont" },
			                    { "mData": "status", "render": function(data, type, row) {
			                        return 'Active'; // Since we're only showing Active employees
			                    }},
			                   
			                ]
			            });
			        },
			        error: function(e) {
			            alert('Failed to fetch JSON data' + e);
			        }
			    });
			}

			async function getLinkedBankDetail() {
			   var employerid = document.getElementById("employerId").value;
			    const clientKey = "client-secret-key"; 
			    const secretKey = "0123456789012345"; 
			    const dataString = employerid + clientKey + secretKey;

			    // Generate SHA-256 hash
			    const encoder = new TextEncoder();
			    const data = encoder.encode(dataString);
			    const hashBuffer = await crypto.subtle.digest("SHA-256", data);
			    const hashArray = Array.from(new Uint8Array(hashBuffer));
			    const hashHex = hashArray.map(byte => byte.toString(16).padStart(2, '0')).join('');

			    $.ajax({
			        type: "POST",
			        url: "/getErupiLinkDlinkAccountDetail",
			        data: {
			            "orgId": employerid,
			            "clientKey": clientKey,
			            "hash": hashHex
			        },
			        success: function (data) {
			            newData = data;
			            var data1 = jQuery.parseJSON(newData);
			            var data2 = data1.data;

			            if (data2.length === 0) {
			                $("#linkaccbankform").show();
			                $("#linkedbnkacntsctn").hide();
			            } else {
			                $("#linkaccbankform").hide();
			                $("#linkedbnkacntsctn").show();
			            }

			            const wrapper = document.getElementById('data-wrapper');
			            wrapper.innerHTML = '';

			            // Render only specific fields
			            data1.data.forEach(item => {
			                const container = document.createElement('div');
			                container.className = 'data-container';

			                const fieldsToDisplay = [
			                    "bankName", "accountHolderName", "acNumber", "accountType", "ifsc", 
			                    "mobile", "merchentIid", "submurchentid", "payerva"
			                ];

			                const fieldLabels = {
			                    bankName: "Bank Name",
			                    accountHolderName: "Account Holder Name",
			                    acNumber: "Account Number",
			                    accountType: "Account Type",
			                    ifsc: "IFSC",
			                    mobile: "Mobile",
			                    merchentIid: "Merchant Id",
			                    submurchentid: "Sub Merchant Id",
			                    payerva: "Payerva",
			                };

			                fieldsToDisplay.forEach(key => {
			                    const fieldDiv = document.createElement('div');
			                    fieldDiv.className = 'field';
			                    fieldDiv.innerHTML = `<span class="label">${fieldLabels[key]}:</span> ${item[key] ?? 'N/A'}`;
			                    container.appendChild(fieldDiv);
			                });

			                // ❌ Removed "Set As Primary" button
			                // ❌ Removed "De-Link / Link Bank A/C" button

			                wrapper.appendChild(container);
			            });
			        },
			        error: function (e) {
			            alert('Error: ' + e);
			        }
			    });
			}
