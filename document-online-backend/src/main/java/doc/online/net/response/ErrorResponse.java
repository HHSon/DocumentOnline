package doc.online.net.response;

public class ErrorResponse extends BaseResponse {
	public ErrorResponse() {
		this.status = ResponseCode.ERROR; // default
	}

	public ErrorResponse(String status, String msg) {
		super(status, msg);
	}
}
