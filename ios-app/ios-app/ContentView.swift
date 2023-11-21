//
//  ContentView.swift
//  ios-app
//
//  Created by More, Sameer on 9/18/23.
//

import SwiftUI
import SharedComposeUI

struct ContentView: View {
    var body: some View {
        VStack {
            HomeScreen().background(Color.red)
        }
    }
}

struct HomeScreen: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        let controller = HomeScreenKt.HomeScreenViewController()
        return controller
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
