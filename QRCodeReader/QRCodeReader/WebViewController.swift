//
//  WebViewController.swift
//  QRCodeReader
//
//  Created by Umberto D'Ovidio on 03/08/17.
//  Copyright © 2017 Umberto D'Ovidio. All rights reserved.
//

import UIKit
import WebKit

class WebViewController: UIViewController, WKUIDelegate {

    var urlString = ""
    var webView: WKWebView!
    
    override func loadView() {
        let webConfiguration = WKWebViewConfiguration()
        webView = WKWebView(frame: .zero, configuration: webConfiguration)
        webView.uiDelegate = self
        view = webView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let myURL = URL(string: urlString)
        let request = URLRequest(url: myURL!)
        webView.load(request)
    }
}
