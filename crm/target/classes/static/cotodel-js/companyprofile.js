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
